package com.example.journalist_media_manager_springboot.persistence.repository;

import com.example.journalist_media_manager_springboot.persistence.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
