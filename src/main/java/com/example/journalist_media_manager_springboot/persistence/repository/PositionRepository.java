package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.Position;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface PositionRepository extends CrudRepository<Position, Integer> {

    public Set<Position> findAllByPositionNameIn(Set<String> names);

    @Override
    Collection<Position> findAll();
}
