package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    public Set<Media> findAllByMediaNameIn(Set<String> names);
    public Optional<Media> findById(Integer id);
}
