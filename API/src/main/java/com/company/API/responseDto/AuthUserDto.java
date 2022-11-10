package com.company.API.responseDto;

import lombok.Data;

@Data
public class AuthUserDto {
    private String username;

    private String password;

    public AuthUserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
