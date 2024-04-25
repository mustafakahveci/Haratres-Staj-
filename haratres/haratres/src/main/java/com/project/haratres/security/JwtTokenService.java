package com.project.haratres.security;

import io.jsonwebtoken.*;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

    @Resource
    private final CustomUserDetailsService customUserDetailsService;

    public static String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    //imzalama ve doğrulama işlemleri için gereklidir. Büyük projelerde kod içine gömülmemelidir.

    public String generateToken(String username){
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        /*if(userDetails.getAuthorities().iterator().next().toString().equals("CUSTOMER")){
            SECRET = "haratres";
        }else{
            SECRET = "haratres2";
        }*/
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuer(userDetails.getAuthorities().iterator().next().toString())
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
