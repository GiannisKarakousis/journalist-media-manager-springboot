package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.Journalist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JournalistRepository extends JpaRepository<Journalist, Integer> {

    public Optional<Journalist> findById(Integer id);

    //public Page<Journalist> findAll(Specification<Journalist> specification, Pageable pageable);
}
