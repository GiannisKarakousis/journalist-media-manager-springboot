package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.Reportage;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface ReportageRepository extends CrudRepository<Reportage, Integer> {

    public Set<Reportage> findAllByReportageNameIn(Set<String> names);

    @Override
    public Collection<Reportage> findAll();
}
