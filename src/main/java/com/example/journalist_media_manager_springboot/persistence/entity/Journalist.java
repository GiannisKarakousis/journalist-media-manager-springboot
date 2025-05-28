package com.example.journalist_media_manager_springboot.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "journalist")
public class Journalist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @CreationTimestamp
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_user", referencedColumnName = "id", updatable = false)
    @CreatedBy
    private User createdByUser;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "date_updated")
    private Date dateUpdated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updated_by_user", referencedColumnName = "id", insertable = false, updatable = false)
    @LastModifiedBy
    private User updatedByUser;

    @Basic(optional = false)
    @NotNull
    @Column(name = "gdpr")
    private Boolean gdpr;

    @Column(name = "first_name")
    private String firstname;

    @Basic(optional = false)
    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastname;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_position_id", referencedColumnName = "id")
    private DepartmentPosition departmentPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accreditation_id", referencedColumnName = "id")
    private Accreditation accreditation;

    @JoinTable(name = "journalist_has_customer", joinColumns = {
            @JoinColumn(name = "journalist_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "customer_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Customer> customerCollection;

    @JoinTable(name = "journalist_has_media", joinColumns = {
            @JoinColumn(name = "journalist_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "media_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Media> mediaCollection;

    @JoinTable(name = "journalist_has_position", joinColumns = {
            @JoinColumn(name = "journalist_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "position_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Position> positionCollection;

    @JoinTable(name = "journalist_has_reportage", joinColumns = {
            @JoinColumn(name = "journalist_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "reportage_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Reportage> reportageCollection;

    @Basic
    @Column(name = "phone_1")
    private String phone1;

    @Basic
    @Column(name = "phone_2")
    private String phone2;

    @Basic
    @Column(name = "mobile_1")
    private String mobile1;

    @Basic
    @Column(name = "mobile_2")
    private String mobile2;

    @Basic
    @Column(name = "email_1")
    private String email1;

    @Basic
    @Column(name = "email_2")
    private String email2;

    @Basic
    @Column(name = "keywords")
    private String keywords;

    @Basic
    @Column(name = "notes")
    private String notes;

    public Journalist() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public User getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(User updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    public Boolean isGdpr() {
        return gdpr;
    }

    public void setGdpr(Boolean gdpr) {
        this.gdpr = gdpr;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public DepartmentPosition getDepartmentPosition() {
        return departmentPosition;
    }

    public void setDepartmentPosition(DepartmentPosition departmentPosition) {
        this.departmentPosition = departmentPosition;
    }

    public Accreditation getAccreditation() {
        return accreditation;
    }

    public void setAccreditation(Accreditation accreditation) {
        this.accreditation = accreditation;
    }

    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    public Collection<Media> getMediaCollection() {
        return mediaCollection;
    }

    public void setMediaCollection(Collection<Media> mediaCollection) {
        this.mediaCollection = mediaCollection;
    }

    public Collection<Position> getPositionCollection() {
        return positionCollection;
    }

    public void setPositionCollection(Collection<Position> positionCollection) {
        this.positionCollection = positionCollection;
    }

    public Collection<Reportage> getReportageCollection() {
        return reportageCollection;
    }

    public void setReportageCollection(Collection<Reportage> reportageCollection) {
        this.reportageCollection = reportageCollection;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
