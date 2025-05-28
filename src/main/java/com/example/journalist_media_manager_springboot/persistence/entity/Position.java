package com.example.journalist_media_manager_springboot.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "position")
public class Position implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "position_name", unique = true)
    private String positionName;

//    @JoinTable(name = "journalist_has_position", joinColumns = {
//            @JoinColumn(name = "position_id", referencedColumnName = "id")}, inverseJoinColumns = {
//            @JoinColumn(name = "journalist_id", referencedColumnName = "id")})
//    @ManyToMany
//    private Collection<Journalist> journalistCollection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

//    public Collection<Journalist> getJournalistCollection() {
//        return journalistCollection;
//    }
//
//    public void setJournalistCollection(Collection<Journalist> journalistCollection) {
//        this.journalistCollection = journalistCollection;
//    }
}
