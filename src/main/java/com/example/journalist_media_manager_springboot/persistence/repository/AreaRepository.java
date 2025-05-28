package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.Area;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface AreaRepository extends CrudRepository<Area, Integer> {

    public Optional<Area> findByAreaName(String name);

    @Override
    public Collection<Area> findAll();
}
