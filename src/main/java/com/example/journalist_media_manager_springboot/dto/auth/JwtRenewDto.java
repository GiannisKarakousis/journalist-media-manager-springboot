package com.example.journalist_media_manager_springboot.dto.auth;

public class JwtRenewDto {

    private String refreshAccessToken;

    public String getRefreshAccessToken() {
        return refreshAccessToken;
    }

    public void setRefreshAccessToken(String refreshAccessToken) {
        this.refreshAccessToken = refreshAccessToken;
    }

}