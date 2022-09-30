package com.company.services;

import com.company.API.model.EmployeeDto;
import com.company.API.responseDto.AuthUserDto;
import com.company.config.JwtTokenAdapter;
import com.company.domain.models.Employee;
import com.company.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenAdapter jwtTokenAdapter;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder encoder, AuthenticationManager authenticationManager, JwtTokenAdapter jwtTokenAdapter) {
        this.employeeRepository = employeeRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenAdapter = jwtTokenAdapter;
    }

    public void addEmployee(@RequestBody EmployeeDto employeeDto){
        Employee.Positions position = null;

        for (Employee.Positions pos : Employee.Positions.values()){
            if (pos.getPosition().equals(employeeDto.getPosition()))
                position = pos;
        }

        employeeRepository.save(
                Employee.builder()
                        .name(employeeDto.getName())
                        .surname(employeeDto.getSurname())
                        .username(employeeDto.getUsername())
                        .password(encoder.encode(employeeDto.getPassword()))
                        .position(position)
                        .build()
        );
    }

    public String authenticate (AuthUserDto dto){
        String username = dto.getUsername();
        String password = dto.getPassword();
        Optional<Employee> optional = employeeRepository.findByUsername(username);
        if (optional.isPresent()){
            if (optional.get().getPassword().equals(password)){
                return optional.get().getUsername();
            }
        }
        return null;
    }
}
