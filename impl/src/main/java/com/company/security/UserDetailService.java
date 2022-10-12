package com.company.security;

import com.company.domain.models.Employee;
import com.company.repositories.EmployeeRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public UserDetailService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = employeeRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not"));

        return UserDetail.getInstance(employee);
    }
}
