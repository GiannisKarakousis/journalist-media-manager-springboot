package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.MediaGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface MediaGroupRepository extends CrudRepository<MediaGroup, Integer> {

    public Optional<MediaGroup> findByMediaGroupName(String name);

    @Override
    Collection<MediaGroup> findAll();
}
