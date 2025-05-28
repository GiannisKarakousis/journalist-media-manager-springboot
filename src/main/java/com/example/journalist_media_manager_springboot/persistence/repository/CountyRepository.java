package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.County;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface CountyRepository extends CrudRepository<County, Integer> {

    public Optional<County> findByCountyName(String name);

    @Override
    public Collection<County> findAll();
}
