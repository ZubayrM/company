package com.company.config;

import com.company.domain.models.Employee;
import com.company.repositories.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
}
