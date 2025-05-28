package com.example.journalist_media_manager_springboot.service.user;

import com.example.journalist_media_manager_springboot.dto.register.UserRegisterDto;
import com.example.journalist_media_manager_springboot.dto.user.UserDto;
import com.example.journalist_media_manager_springboot.exception.RoleNotFoundException;
import com.example.journalist_media_manager_springboot.exception.UserAlreadyExistAuthenticationException;
import com.example.journalist_media_manager_springboot.persistence.entity.Role;
import com.example.journalist_media_manager_springboot.persistence.entity.User;
import com.example.journalist_media_manager_springboot.persistence.repository.RoleRepository;
import com.example.journalist_media_manager_springboot.persistence.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;


@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;

    public UserDto registerUser(UserRegisterDto userRegisterDto) {
        if (userRepository.existsByUsername(userRegisterDto.getUsername())) {
            throw new UserAlreadyExistAuthenticationException("Username already taken!");
        }

        Role role = roleRepository.findById(userRegisterDto.getRoleId())
                .orElseThrow(() -> new RoleNotFoundException("Role with ID " + userRegisterDto.getRoleId() + " not found!"));

        User user = new User();
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setFirstname(userRegisterDto.getFirstname());
        user.setLastname(userRegisterDto.getLastname());
        user.setEmail(userRegisterDto.getEmail());
        user.setRole(role);
        user.setFailedLoginAttempts(0);
        user.setEnabled(userRegisterDto.isEnabled());

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        user.setDateCreated(timestamp);

        User createdUser = userRepository.save(user);
        return modelMapper.map(createdUser, UserDto.class);
    }
}
