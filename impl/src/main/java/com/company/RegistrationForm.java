package com.company;

import com.company.domain.models.Employee;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String name;
    private String surname;
    private String username;
    private String password;
    private Employee.Positions position;

    public Employee toEmployee (PasswordEncoder passwordEncoder){
        return new Employee(name, surname, username, passwordEncoder.encode(password),
                position);
    }
}
