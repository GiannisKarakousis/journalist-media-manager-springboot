package com.example.journalist_media_manager_springboot.dto.journalist.register;

public class JournalistCreateRequiredDto {

    private Boolean gdpr;
    private String lastname;

    public Boolean getGdpr() {
        return gdpr;
    }

    public void setGdpr(Boolean gdpr) {
        this.gdpr = gdpr;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
