package com.company.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers( "/api/login/", "/api/login/registration", "/").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(jwtTokenConfig)
                .and()
                .logout()
                .logoutRequestMatcher(new RegexRequestMatcher("/api/logout", "GET"))
                .deleteCookies(authKey).logoutSuccessUrl("/")
                .and()
                .formLogin()
                .loginPage("/api/login/");
                //.and()
//                .antMatchers("/").access("permitAll()")
//                .and()
//                .formLogin().loginPage("/api/login")
//                .loginProcessingUrl("/authenticate");
    }
}
