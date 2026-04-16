package com.example.pos_transaction_service.security;

import java.util.Date;

import java.security.Key;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;

public class JwtUtil {
	private static final String SECRET_KEY = "mySecretKeymySecretKeymySecretKey12";
	
	private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

}
