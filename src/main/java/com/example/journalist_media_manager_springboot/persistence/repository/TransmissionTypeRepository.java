package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.TransmissionType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface TransmissionTypeRepository extends CrudRepository<TransmissionType, Integer> {

    public Optional<TransmissionType> findByTransmissionTypeName(String name);

    @Override
    public Collection<TransmissionType> findAll();
}
