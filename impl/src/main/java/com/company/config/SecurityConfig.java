package com.company.config;

import com.company.domain.models.Employee;
import com.company.repositories.EmployeeRepository;
import com.company.security.UserDetail;
import com.company.security.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public UserDetail userDetailsService(EmployeeRepository employeeRepository){
        return username -> {
            UserDetailService userDetailService = new UserDetailService(username);

            throw new UsernameNotFoundException("Пользователь '" + username + "' не найден!");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers( "/api/login", "/api/login/registration").permitAll()
                .anyRequest().authenticated()
                .and()
//                .antMatchers("/").access("permitAll()")
//                .and()
                .formLogin().loginPage("/api/login")
                .loginProcessingUrl("/authenticate")
                .and()
                .build();
    }
}
