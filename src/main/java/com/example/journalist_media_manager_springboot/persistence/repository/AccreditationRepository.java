package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.Accreditation;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface AccreditationRepository extends CrudRepository<Accreditation, Integer> {

    public Optional<Accreditation> findByAccreditationName(String name);

    @Override
    public Collection<Accreditation> findAll();
}
