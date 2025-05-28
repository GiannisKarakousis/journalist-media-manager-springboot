package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface CountryRepository extends CrudRepository<Country, Integer> {

    public Optional<Country> findByCountryName(String name);

    @Override
    public Collection<Country> findAll();
}