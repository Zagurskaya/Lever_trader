package com.gmail.zagurskaya.service.security;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class AppUserPrincipal implements UserDetails {

    private UserDTO user;
    private Set<GrantedAuthority> grantedAuthorities;

    public AppUserPrincipal(UserDTO userDto) {
        this.user = userDto;
        this.grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(userDto.getRole()));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
