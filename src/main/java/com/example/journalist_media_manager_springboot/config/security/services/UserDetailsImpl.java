package com.example.journalist_media_manager_springboot.config.security.services;

import com.example.journalist_media_manager_springboot.dto.user.RoleDto;
import com.example.journalist_media_manager_springboot.persistence.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class UserDetailsImpl implements UserDetails {

    private final Integer id;
    private final String username;
    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private RoleDto roleDto;
    private Boolean isEnabled;
    private Integer failedLoginAttempts;
    private Date latestFailedLogin;


    public UserDetailsImpl(Integer id, String username, String password, String email, Collection<? extends GrantedAuthority> authorities,
                           RoleDto roleDto, Boolean isEnabled, Integer failedLoginAttempts, Date latestFailedLogin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.roleDto = roleDto;
        this.isEnabled = isEnabled;
        this.failedLoginAttempts = failedLoginAttempts;
        this.latestFailedLogin = latestFailedLogin;
    }

    public static UserDetailsImpl build(User user) {
        // Create a single GrantedAuthority from the Role
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());

        // Create a RoleDto instance for the Role
        RoleDto roleDto = new RoleDto();
        roleDto.setId(user.getRole().getId());
        roleDto.setRoleName(user.getRole().getRoleName());

        // Return a new instance of UserDetailsImpl
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                Collections.singleton(authority), // Single authority wrapped in a list
                roleDto,
                user.isEnabled(),
                user.getFailedLoginAttempts(),
                user.getLatestFailedLogin()
        );
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Integer getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Integer failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public Date getLatestFailedLogin() {
        return latestFailedLogin;
    }

    public void setLatestFailedLogin(Date latestFailedLogin) {
        this.latestFailedLogin = latestFailedLogin;
    }
}
