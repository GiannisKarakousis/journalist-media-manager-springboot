package com.example.journalist_media_manager_springboot.config;

import com.example.journalist_media_manager_springboot.component.AuditorAwareImpl;
import com.example.journalist_media_manager_springboot.persistence.entity.User;
import com.example.journalist_media_manager_springboot.persistence.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware") // ✅ Make sure this is present!
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper;
    }

    @Bean
    public AuditorAware<User> auditorAware(UserRepository userRepository) {
        return new AuditorAwareImpl(userRepository); // ✅ Register AuditorAwareImpl as a bean
    }
}