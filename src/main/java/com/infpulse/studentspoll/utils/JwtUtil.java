package com.infpulse.studentspoll.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {
    @Value("{TOKEN_SECRET_KEY}")
    private static String SECRET_KEY;

    public static String extractUserLogin(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails) {
        // здесь можно добавить пары ключ-значения
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    public boolean tokenIsValid(String token, UserDetails userDetails) {
        final String userLogin = extractUserLogin(token);
        return userLogin.equals(userDetails.getUsername()) && !tokenIsExpired(token);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(5)))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    private boolean tokenIsExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    private static <T> T extractClaim(String token, Function<? super Claims, T> extractor) {
        final Claims claims = extractAllClaims(token);
        return extractor.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
