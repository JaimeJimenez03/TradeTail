package app.platform.security.service;

import app.platform.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MiUserDetails implements UserDetails {

    private String email;
    private String password;
    private boolean active;
    private Collection<? extends GrantedAuthority> authorities;

    public MiUserDetails(User user) {
        this.email = user.getEmail(); // Usa el correo en lugar de username
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_"));
    }

    @Override
    public String getUsername() {
        return email;  // Aquí devolvemos el email
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
}