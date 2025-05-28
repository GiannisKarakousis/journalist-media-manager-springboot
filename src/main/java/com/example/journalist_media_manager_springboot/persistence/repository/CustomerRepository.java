package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    public Set<Customer> findAllByCustomerNameIn(Set<String> names);

    @Override
    public Collection<Customer> findAll();
}
