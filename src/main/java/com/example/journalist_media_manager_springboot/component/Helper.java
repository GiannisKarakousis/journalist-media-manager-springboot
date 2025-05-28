package com.example.journalist_media_manager_springboot.component;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

public class Helper {

    public static String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }
}
