package com.example.lab5.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users") // or whatever your table is
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    // Required by JPA
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters for id, username, password

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username; }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    // Spring Security-related methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // No roles for now
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
