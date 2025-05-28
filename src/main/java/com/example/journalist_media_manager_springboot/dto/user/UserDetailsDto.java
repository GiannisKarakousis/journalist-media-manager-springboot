package com.example.journalist_media_manager_springboot.dto.user;

public class UserDetailsDto {

    private Integer id;
    private String username;
    private String email;
    private RoleDto roleDto;

    public UserDetailsDto() {
    }

    public UserDetailsDto(String username, RoleDto roleDto) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roleDto = roleDto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }
}
