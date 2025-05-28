package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.Periodicity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PeriodicityRepository extends CrudRepository<Periodicity, Integer> {

    public Optional<Periodicity> findByPeriodicityName(String name);

    public Collection<Periodicity> findAllByOrderById();
}
