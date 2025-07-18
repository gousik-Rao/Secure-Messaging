package com.military.Security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private static String secret_Key;
	
//	Generate JST Token
	public static String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();	
	}
	
//	Extract username
	public static String extractUsername(String token) {
			return Jwts.parserBuilder()
								.setSigningKey(getSigningKey())
								.build()
								.parseClaimsJws(token)
								.getBody()
								.getSubject();
	}
	
//	Converting SECRET_KEY to secure key instance
	private static SecretKey getSigningKey() {
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













