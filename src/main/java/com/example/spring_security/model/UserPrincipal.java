package com.example.spring_security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private Users users;

    public UserPrincipal(Users users) {
        this.users = users;
    }
    // Principal - currently validating object

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+users.getRole()));
    }

    @Override
    public String getPassword() {
        return users.getPassword(); // From DB
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }
}
