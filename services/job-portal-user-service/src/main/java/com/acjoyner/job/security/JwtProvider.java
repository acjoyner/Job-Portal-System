package com.acjoyner.job.security;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtProvider {
    private final SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(Authentication authentication, Long userId) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = populatedAuthories(authorities);
        return Jwts.builder()
                .issuedAt(new java.util.Date())
                .expiration(new java.util.Date(System.currentTimeMillis() + 86400000))
                .claim("email", authentication.getName()) // 24 hours
                .claim("authorities", role)
                .claim("userId", userId)
                .signWith(secretKey)
                .compact();
    }

    private String populatedAuthories(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority authority : authorities) {
            auths.add(authority.getAuthority());
        }
        return String.join(",", auths );
    }

}
