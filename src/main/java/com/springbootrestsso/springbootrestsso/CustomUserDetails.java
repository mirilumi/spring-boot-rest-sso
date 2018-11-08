package com.springbootrestsso.springbootrestsso;

import com.springbootrestsso.springbootrestsso.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CustomUserDetails implements UserDetails {
    private String username;

    private String password;

    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User byUsername) {
//        if (byUsername.isPresent()){
            this.username = byUsername.getUsername();
            this.password = byUsername.getPassword();
            List<GrantedAuthority> grants = new ArrayList<>();
            byUsername.getRoles().forEach(t->{
                grants.add(new SimpleGrantedAuthority(t.getName().toUpperCase()));
            });
            this.authorities = grants;
//        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
