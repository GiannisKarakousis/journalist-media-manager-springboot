package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    public Optional<User> findByUsername(String username);

    public Optional<User> findById(Integer id);

    public Boolean existsByUsername(String username);

    @Override
    public Collection<User> findAll();
}
