package com.company.config;

import com.company.domain.models.Employee;
import com.company.repositories.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(EmployeeRepository employeeRepository){
        return username -> {
            Employee employee = employeeRepository.findByUsername(username);
            if (employee != null)
                return employee;

            throw new UsernameNotFoundException("Пользователь '" + username + "' не найден!");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers( "/api/login", "/api/login/registration").permitAll()
                .anyRequest().authenticated()
                .and()
//                .antMatchers("/").access("permitAll()")
//                .and()
                .formLogin().loginPage("/api/login")
                .loginProcessingUrl("/authenticate")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .build();
    }
}
