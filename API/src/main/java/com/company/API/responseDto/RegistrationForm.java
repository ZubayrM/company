package com.company.API.responseDto;

import com.company.domain.models.Employee;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String position;

    public Employee toEmployee (PasswordEncoder encoder){
        Employee employee = Employee.builder()
                .name(name)
                .surname(surname)
                .username(username)
                .password(encoder.encode(password))
                .position(Employee.Positions.valueOf(position)).build();

        return employee;
    }
}
