package com.project.haratres.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenService {

    private final CustomUserDetailsService customUserDetailsService;

    public static final String SECRET = "haratres";
    //imzalama ve doğrulama işlemleri için gereklidir. Büyük projelerde kod içine gömülmemelidir.

    public String generateToken(String username){
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  // 1 saat
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
/*
    boolean validateToken(String token){
        try {
            Claims claims = getClaims(token);
            return !isTokenExpired(claims.getExpiration());
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException
                 | UnsupportedJwtException | IllegalArgumentException e){
            return false;
        }
    }

    private boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }*/


}
