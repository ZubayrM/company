package com.company.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${authkey}")
    private String authKey;

    private final JwtTokenFilter jwtTokenFilter;
    private final JwtTokenConfig jwtTokenConfig;

    @Autowired
    public WebSecurityConfig(JwtTokenFilter jwtTokenFilter, JwtTokenConfig jwtTokenConfig) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.jwtTokenConfig = jwtTokenConfig;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
        //return new BCryptPasswordEncoder(12);
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers("/api/product/").hasRole("APPROVER")
                .antMatchers( "/api/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(jwtTokenConfig)
                .and()
                .logout()
                .logoutRequestMatcher(new RegexRequestMatcher("/api/logout", "GET"))
                .deleteCookies(authKey).logoutSuccessUrl("/")
                .and()
                .formLogin()
                .loginPage("/api/login/")
                .loginProcessingUrl("/api/login/")
                .defaultSuccessUrl("/api/product/", true);
    }
}
