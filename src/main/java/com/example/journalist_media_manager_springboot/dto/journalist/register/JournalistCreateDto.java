package com.example.journalist_media_manager_springboot.dto.journalist.register;

import com.example.journalist_media_manager_springboot.dto.journalist.CustomerDto;
import com.example.journalist_media_manager_springboot.dto.journalist.PositionDto;
import com.example.journalist_media_manager_springboot.dto.journalist.ReportageDto;
import com.example.journalist_media_manager_springboot.dto.media.MediaDto;

import java.util.Collection;

public class JournalistCreateDto {

    private Boolean gdpr;
    private String firstname;
    private String lastname;
    private String departmentPositionName;
    private String accreditationName;
    private Collection<CustomerDto> customerCollection;
    private Collection<MediaDto> mediaCollection;
    private Collection<PositionDto> positionCollection;
    private Collection<ReportageDto> reportageCollection;
    private String phone1;
    private String phone2;
    private String mobile1;
    private String mobile2;
    private String email1;
    private String email2;
    private String keywords;
    private String notes;

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

    public String getDepartmentPositionName() {
        return departmentPositionName;
    }

    public void setDepartmentPositionName(String departmentPositionName) {
        this.departmentPositionName = departmentPositionName;
    }

    public String getAccreditationName() {
        return accreditationName;
    }

    public void setAccreditationName(String accreditationName) {
        this.accreditationName = accreditationName;
    }

    public Collection<CustomerDto> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<CustomerDto> customerCollection) {
        this.customerCollection = customerCollection;
    }

    public Collection<MediaDto> getMediaCollection() {
        return mediaCollection;
    }

    public void setMediaCollection(Collection<MediaDto> mediaCollection) {
        this.mediaCollection = mediaCollection;
    }

    public Collection<PositionDto> getPositionCollection() {
        return positionCollection;
    }

    public void setPositionCollection(Collection<PositionDto> positionCollection) {
        this.positionCollection = positionCollection;
    }

    public Collection<ReportageDto> getReportageCollection() {
        return reportageCollection;
    }

    public void setReportageCollection(Collection<ReportageDto> reportageCollection) {
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
