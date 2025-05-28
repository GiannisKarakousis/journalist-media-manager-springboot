package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.MediaType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface MediaTypeRepository extends CrudRepository<MediaType, Integer> {

    public Optional<MediaType> findByMediaTypeName(String name);

    @Override
    public Collection<MediaType> findAll();
}
