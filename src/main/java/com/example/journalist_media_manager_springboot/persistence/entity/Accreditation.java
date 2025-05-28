package com.example.journalist_media_manager_springboot.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "accreditation")
public class Accreditation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "accreditation_name", unique = true)
    private String accreditationName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccreditationName() {
        return accreditationName;
    }

    public void setAccreditationName(String accreditationName) {
        this.accreditationName = accreditationName;
    }
}
