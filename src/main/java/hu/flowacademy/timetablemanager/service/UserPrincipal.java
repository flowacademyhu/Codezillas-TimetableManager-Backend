package hu.flowacademy.timetablemanager.service;

import hu.flowacademy.timetablemanager.model.Role;
import hu.flowacademy.timetablemanager.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserPrincipal implements UserDetails {
    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        Set<Role> roles = user.getRoles();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole() ));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override   // expired password option
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override   // locked accout option
    public boolean isEnabled() {
        return user.isEnabled();
    }
}