package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.auth.UserLoginDto;
import com.example.journalist_media_manager_springboot.dto.register.UserRegisterDto;
import com.example.journalist_media_manager_springboot.dto.user.UserDto;
import com.example.journalist_media_manager_springboot.service.user.RegisterService;
import com.example.journalist_media_manager_springboot.service.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class PublicController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDto register(@RequestBody UserRegisterDto userRegisterDto) {
        return registerService.registerUser(userRegisterDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginDto loginRequest, HttpServletResponse response) throws IOException {
        return this.userService.logonUser(loginRequest, response);
    }
}
