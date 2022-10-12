package com.company.security;

import com.company.domain.models.Employee;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class UserDetail implements UserDetails {

    private String username;
    private String password;
    private Set<SimpleGrantedAuthority> roles;
    private boolean isBlocked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return username;
    }

    @Override
    public String getUsername() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isBlocked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isBlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isBlocked;
    }

    @Override
    public boolean isEnabled() {
        return !isBlocked;
    }

    public static UserDetail getInstance(Employee employee){
        return new UserDetail(
          employee.getUsername(),
          employee.getPassword(),
          Stream.of(new SimpleGrantedAuthority(employee.getPosition().name()))
                .collect(Collectors.toSet()),
                employee.getIsBlocked()
        );
    }
}
