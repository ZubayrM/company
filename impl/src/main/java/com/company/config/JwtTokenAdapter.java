package com.company.config;

import com.company.repositories.EmployeeRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.Date;

@Component
public class JwtTokenAdapter {

    private final EmployeeRepository employeeRepository;
    private String secret = "KEMZ";

    @Autowired
    public JwtTokenAdapter(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public String createToken (String username){
        Date date = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(date.getTime() * 100_000))
                .signWith(SignatureAlgorithm.ES512, secret)
                .compact();
    }


}
