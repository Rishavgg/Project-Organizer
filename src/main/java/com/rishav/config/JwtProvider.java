package com.rishav.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;

//@Configurable
public class JwtProvider {
    static SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstant.SECURITY_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        String jwt = Jwts.builder().setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)))
                .claim("email", auth.getName())
                .signWith(secretKey).compact();

        return jwt;
    }

    public static String getEmailFromToken(String jwt) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();

        return String.valueOf(claims.get("email"));
    }
}
