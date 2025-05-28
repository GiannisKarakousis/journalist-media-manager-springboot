package com.example.journalist_media_manager_springboot.service.user;

import com.example.journalist_media_manager_springboot.config.security.jwt.JwtUtils;
import com.example.journalist_media_manager_springboot.config.security.services.UserDetailsImpl;
import com.example.journalist_media_manager_springboot.dto.auth.JwtDto;
import com.example.journalist_media_manager_springboot.dto.auth.UserLoginDto;
import com.example.journalist_media_manager_springboot.dto.user.UserDetailsDto;
import com.example.journalist_media_manager_springboot.dto.user.UserDto;
import com.example.journalist_media_manager_springboot.exception.UserNotFoundException;
import com.example.journalist_media_manager_springboot.persistence.entity.User;
import com.example.journalist_media_manager_springboot.persistence.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Value("${security.maxAllowFailLogins}")
    private Integer maxAllowFailLogins;

    @Value("${security.loginLockTimeInMinutes}")
    private Integer loginLockTimeInMinutes;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserDto findById(Integer id) throws UserNotFoundException {
        Optional<User> optionalUserEntity = this.userRepository.findById(id);

        if (optionalUserEntity.isPresent()) {
            return modelMapper.map(optionalUserEntity.get(), UserDto.class);
        } else {
            throw new UserNotFoundException();
        }
    }

    public Collection<UserDto> findAllUsers() {
        Collection<User> users = this.userRepository.findAll();

        Type type = new TypeToken<Collection<UserDto>>() {
        }.getType();

        return modelMapper.map(users, type);
    }

    public User getUserByUserName(String userName) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUsername(userName);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User not found UserService:getUserByUserName");
        }
    }

    public Date getLockedUntil(Date latestFailedLogin) {
        if (latestFailedLogin == null) {
            return null;
        }
        return new Date(latestFailedLogin.getTime() + this.loginLockTimeInMinutes * 60 * 1000);
    }

    public Boolean userLoginIsLockedByTry(Integer failedLoginAttempts, Date latestFailedLogin) {
        Date now = new Date();
        Date lockedUntil = this.getLockedUntil(latestFailedLogin);

        if (failedLoginAttempts < this.maxAllowFailLogins || now.after(lockedUntil)) {
            return false;
        } else {
            return true;
        }
    }

    private void unlockUserLogin(User user) {
        user.setFailedLoginAttempts(0);
        user.setLatestFailedLogin(null);
        userRepository.save(user);
    }

    private void handleBadCredentials(User user) {
        Integer userPrevLoginAttempts = user.getFailedLoginAttempts();

        user.setFailedLoginAttempts(userPrevLoginAttempts + 1);
        user.setLatestFailedLogin(new Date());
        logger.error("handleBadCredentials wrong password for username:{} ", user.getUsername());
        userRepository.save(user);
    }

    private User preLogonHandler(String username) {
        User user = new User();
        try {
            user = this.getUserByUserName(username);
            Integer userPrevFailedLoginAttempts = user.getFailedLoginAttempts();
            Date prevFailedLoginDate = user.getLatestFailedLogin();
            if (this.userLoginIsLockedByTry(userPrevFailedLoginAttempts, prevFailedLoginDate)) {
                throw new BadCredentialsException("handleBadCredentials user login is locked");
            }
            return user;
        } catch (UserNotFoundException e) {
            throw new BadCredentialsException("handleBadCredentials user login is locked");
        }
    }

    public ResponseEntity<?> logonUser(UserLoginDto loginRequest, HttpServletResponse response) throws IOException {
        Authentication authentication;

        // Check user and can login
        User user = this.preLogonHandler(loginRequest.getUsername());

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException badCredentialsException) {
            this.handleBadCredentials(user);
            throw badCredentialsException;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

        if (!userDetailsImpl.getEnabled()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Error: User is not active, User db Id: " + userDetailsImpl.getId());
            return ResponseEntity.ok(null);
        }

        // Unlock user
        this.unlockUserLogin(user);

        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setId(userDetailsImpl.getId());
        userDetailsDto.setUsername(userDetailsImpl.getUsername());
        userDetailsDto.setRoleDto(userDetailsImpl.getRoleDto());
        userDetailsDto.setEmail(userDetailsImpl.getEmail());

        String jwt = jwtUtils.generateJwtToken(userDetailsDto);
        //String jwtRefresh = jwtUtils.generateRefreshJwtToken(String.valueOf(userDetails.getId()));

        JwtDto jwtResponse = new JwtDto();

        jwtResponse.setAccessToken(jwt);
        //jwtResponse.setRefreshAccessToken(jwtRefresh);

        return ResponseEntity.ok(jwtResponse);
    }
}
