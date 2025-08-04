package com.military.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	@Value("${spring.app.jwtSecret}")
	private String secret_Key;
	
    @Value("${spring.app.jwtExpirationMs}")
    private int jwtExpirationMs;
	
//	Generate JWT Token
	public String generateToken(String username) {
		if(username == null || username.isBlank())
			throw new IllegalArgumentException("Username cannot be null for token generation");      
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();	
	}
	
//	Extract username
	public String extractUsername(String token) {
			return Jwts.parserBuilder()
								.setSigningKey(getSigningKey())
								.build()
								.parseClaimsJws(token)
								.getBody()
								.getSubject();
	}
	
//	Converting SECRET_KEY to secure key instance
	private SecretKey getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret_Key);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
//	Validate token
	public boolean validateToken(String token) {
		Jwts.parserBuilder()
			.setSigningKey(getSigningKey())
			.build()
			.parseClaimsJws(token);
		return true;
	}
}













