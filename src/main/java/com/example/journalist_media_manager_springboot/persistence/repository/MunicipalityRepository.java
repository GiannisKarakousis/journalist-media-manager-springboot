package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.Municipality;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface MunicipalityRepository extends CrudRepository<Municipality, Integer> {

    public Optional<Municipality> findByMunicipalityName(String name);

    @Override
    public Collection<Municipality> findAll();
}
