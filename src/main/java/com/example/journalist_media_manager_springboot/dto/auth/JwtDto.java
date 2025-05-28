package com.example.journalist_media_manager_springboot.dto.auth;

public class JwtDto {

    private String accessToken;
//    private String refreshAccessToken;
    private String type = "Bearer";

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

//    public String getRefreshAccessToken() {
//        return refreshAccessToken;
//    }
//
//    public void setRefreshAccessToken(String refreshAccessToken) {
//        this.refreshAccessToken = refreshAccessToken;
//    }

    public String getType() {
        return type;
    }
}
