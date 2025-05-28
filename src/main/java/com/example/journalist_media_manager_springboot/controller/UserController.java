package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.user.UserDto;
import com.example.journalist_media_manager_springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/users")
//@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @GetMapping
    public Collection<UserDto> getAllUsers() {
        return userService.findAllUsers();
    }

}
