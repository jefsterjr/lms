package org.arcone.lmsapi.util.config.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.arcone.lmsapi.auth.model.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

import static org.arcone.lmsapi.util.config.security.SecurityConstants.EXPIRATION_TIME;
import static org.arcone.lmsapi.util.config.security.SecurityConstants.SECRET;

@Slf4j
@Component
public class JWTUtils {

    private final ObjectMapper mapper = new ObjectMapper();

    public String generateJwtToken(Authentication authentication) throws JsonProcessingException {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setClaims(Collections.singletonMap("usr", mapper.writeValueAsString(userPrincipal)))
                .setSubject((userPrincipal.getUsername()))
                .setIssuer("org.arcone.lmsapi")
                .setSubject(userPrincipal.username())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes())).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes())).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public UserDetailsImpl getUser(String token) {
        String userJson =   Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes())).build().parseClaimsJws(token).getBody().get("usr", String.class);
        try {
            return mapper.readValue(userJson, UserDetailsImpl.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
