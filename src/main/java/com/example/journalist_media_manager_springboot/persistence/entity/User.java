package com.example.journalist_media_manager_springboot.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "`user`") // Use backticks to escape the reserved keyword
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false) // Foreign key
    private Role role;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "first_name")
    private String firstname;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "last_name")
    private String lastname;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "user_name", unique = true)
    private String username;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "password")
    private String password;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;

    @CreatedDate
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @CreatedBy
    @Column(name = "created_by_user")
    private Integer createdByUser;

    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    @Column(name = "date_updated", nullable = true)
    private Date dateUpdated;

    @LastModifiedBy
    @Column(name = "updated_by_user", nullable = true)
    private Integer updatedByUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_last_login", nullable = true)
    private Date dateLastLogin;

    @Column(name = "date_expires", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dateExpires;

    @Column(name = "never_expires", nullable = true)
    private boolean neverExpires;

    @Basic(optional = false)
    @NotNull
    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "failed_login_attempts")
    private Integer failedLoginAttempts;

    @Column(name = "latest_failed_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date latestFailedLogin;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(Integer createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Integer getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(Integer updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    public Date getDateLastLogin() {
        return dateLastLogin;
    }

    public void setDateLastLogin(Date dateLastLogin) {
        this.dateLastLogin = dateLastLogin;
    }

    public Date getDateExpires() {
        return dateExpires;
    }

    public void setDateExpires(Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public boolean isNeverExpires() {
        return neverExpires;
    }

    public void setNeverExpires(boolean neverExpires) {
        this.neverExpires = neverExpires;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Integer getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Integer failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public Date getLatestFailedLogin() {
        return latestFailedLogin;
    }

    public void setLatestFailedLogin(Date latestFailedLogin) {
        this.latestFailedLogin = latestFailedLogin;
    }
}
