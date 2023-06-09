package com.example.demo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String generateToken(String username) {
		Instant now = Instant.now();
		Instant expiration = now.plus(24, ChronoUnit.HOURS); // Token scadrà dopo 24 ore

		return Jwts.builder().setSubject(username).setIssuedAt(Date.from(now)).setExpiration(Date.from(expiration))
				.signWith(SECRET_KEY).compact();
	}

	public String extractUsername(String token) {
		Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);

		return claimsJws.getBody().getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
