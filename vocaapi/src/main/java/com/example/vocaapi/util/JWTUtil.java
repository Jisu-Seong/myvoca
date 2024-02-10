package com.example.vocaapi.util;

import java.io.IOException;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.crypto.SecretKey;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTUtil {
    // private static String key = "${jwt.secret.key}";

    public static String generateToken(Map<String, Object> valueMap, int min) {
        SecretKey key = null;

        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResource("application.properties").openStream());
            key = Keys.hmacShaKeyFor(((String) properties.get("jwt.secret.key")).getBytes("UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        String jwtStr = Jwts.builder()
                .setHeader(Map.of("typ", "JWT"))
                .setClaims(valueMap)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return jwtStr;
    }

    public static Map<String, Object> validateToken(String token) {
        Map<String, Object> claim = null;
        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResource("application.properties").openStream());
            SecretKey key = Keys.hmacShaKeyFor(((String) properties.get("jwt.secret.key")).getBytes("UTF-8"));

            claim = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token) // 파싱 및 검증, 실패 시 에러
                    .getBody();
        } catch (MalformedJwtException malformedJwtException) {
            throw new CustomJWTException("MalFormed");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new CustomJWTException("Expired");
        } catch (InvalidClaimException invalidClaimException) {
            throw new CustomJWTException("Invalid");
        } catch (JwtException jwtException) {
            throw new CustomJWTException("JWTError");
        } catch (Exception e) {
            throw new CustomJWTException("Error");
        }
        return claim;
    }
}
