package com.employee.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JWTService {

    private EmployeeService employeeService;

    private String key="";

    public JWTService(){//to generate a key
        try {
            KeyGenerator keyGenerator= KeyGenerator.getInstance("HmacSHA256");
           Key  ky=keyGenerator.generateKey();
           key= Base64.getEncoder().encodeToString(ky.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    private Keys keys;

    public String generateToken(String name) {
        Map<String,Object> claims= new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(name)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*30))//this token will be applicable for 30 minutes
                .and()
                .signWith(getKey())
                .compact();

    }

    private SecretKey getKey() {
        byte[] keyBytes= Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private < T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims =extractAllClaim(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaim(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName= extractUserName(token);
        return (userName.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date()) ;
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
