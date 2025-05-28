package com.example.journalist_media_manager_springboot.config.security.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.journalist_media_manager_springboot.component.Helper;
import com.example.journalist_media_manager_springboot.config.security.services.UserDetailsServiceImpl;
import com.example.journalist_media_manager_springboot.dto.user.UserDetailsDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = Helper.parseJwt(request);
            if (jwt != null) {
                Claims claims = jwtUtils.getAllClaimsFromToken(jwt);
                ObjectMapper objectMapper = new ObjectMapper();

                String claimsString = objectMapper.writeValueAsString(claims.get("userDetails", Object.class));
                UserDetailsDto userDetailsDto = objectMapper.readValue(claimsString, UserDetailsDto.class);

                GrantedAuthority authority = new SimpleGrantedAuthority(userDetailsDto.getRoleDto().getRoleName());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetailsDto, null, Collections.singleton(authority)
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (JsonProcessingException | ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            logger.error("Cannot set user authentication by token", e);
        }

        filterChain.doFilter(request, response);
    }
}
