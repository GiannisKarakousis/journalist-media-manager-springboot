package com.example.journalist_media_manager_springboot.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "department_position")
public class DepartmentPosition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "department_position_name", unique = true)
    private String departmentPositionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentPositionName() {
        return departmentPositionName;
    }

    public void setDepartmentPositionName(String departmentPositionName) {
        this.departmentPositionName = departmentPositionName;
    }
}
