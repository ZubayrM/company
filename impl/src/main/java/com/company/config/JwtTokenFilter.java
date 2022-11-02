package com.company.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenAdapter adapter;

    @Autowired
    public JwtTokenFilter(JwtTokenAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            authByCookies((HttpServletRequest) servletRequest);
        }
        catch (AuthenticationException ex){
            SecurityContextHolder.clearContext();
        }
        catch (Exception ex){
            SecurityContextHolder.clearContext();
            ex.printStackTrace();
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void authByCookies(HttpServletRequest servletRequest){
        Cookie[] cookies = servletRequest.getCookies();

        if (cookies != null){
            if (cookies.length > 0){
                Optional<Cookie> authorization = Arrays
                        .stream(cookies)
                        .filter(cookie -> cookie.getName().equals("authenticated")).findFirst();

                authorization
                        .map(Cookie::getValue)
                        .ifPresent(token -> SecurityContextHolder
                        .getContext()
                        .setAuthentication(adapter.authentication(token)));
            }
        }
    }
}
