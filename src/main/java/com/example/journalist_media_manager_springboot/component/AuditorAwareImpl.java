package com.example.journalist_media_manager_springboot.component;

import com.example.journalist_media_manager_springboot.config.security.services.UserDetailsImpl;
import com.example.journalist_media_manager_springboot.persistence.entity.User;
import com.example.journalist_media_manager_springboot.persistence.repository.UserRepository;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<User> { // ✅ Now returning a `User` entity

    private final UserRepository userRepository;

    public AuditorAwareImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Optional<User> user = userRepository.findById(userDetails.getId());

        if (user.isEmpty()) {
            System.out.println("❌ ERROR: Could not find User with ID: " + userDetails.getId());
        } else {
            System.out.println("✅ Auditing: Current user is " + user.get().getUsername());
        }

        return user;
    }
}

