package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.DepartmentPosition;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface DepartmentPositionRepository extends CrudRepository<DepartmentPosition, Integer> {

    public Optional<DepartmentPosition> findByDepartmentPositionName(String name);

    @Override
    public Collection<DepartmentPosition> findAll();
}
