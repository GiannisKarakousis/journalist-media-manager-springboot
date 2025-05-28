package com.example.journalist_media_manager_springboot.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_name", unique = true)
    private String customerName;

//    @JoinTable(name = "journalist_has_customer", joinColumns = {
//            @JoinColumn(name = "customer_id", referencedColumnName = "id")}, inverseJoinColumns = {
//            @JoinColumn(name = "journalist_id", referencedColumnName = "id")})
//    @ManyToMany
//    private Collection<Journalist> journalistCollection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

//    public Collection<Journalist> getJournalistCollection() {
//        return journalistCollection;
//    }
//
//    public void setJournalistCollection(Collection<Journalist> journalistCollection) {
//        this.journalistCollection = journalistCollection;
//    }
}
