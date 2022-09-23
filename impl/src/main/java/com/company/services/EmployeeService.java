package com.company.services;

import com.company.API.model.EmployeeDto;
import com.company.domain.models.Employee;
import com.company.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private PasswordEncoder encoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder encoder) {
        this.employeeRepository = employeeRepository;
        this.encoder = encoder;
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
}
