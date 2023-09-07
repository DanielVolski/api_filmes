package api_filmes.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import api_filmes.domain.entities.User;

@Component
public class JwtUtil {

    @Value("${auth.jwt.secret}")
    private String secret;
    @Value("${auth.jwt-expiration-milliseg}")
    private Long expiration;

    public String generateToken(Authentication authentication) {
        Date expirationDate = new Date(new Date().getTime() + expiration);
        User user = (User) authentication.getPrincipal();
        try {
            Key secrKey = Keys.hmacShaKeyFor(secret.getBytes("UTF-8"));
            return Jwts.builder()
            .setSubject(user.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(expirationDate)
            .signWith(secrKey)
            .compact();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    private Claims getClaims(String token) {
        try {
            Key secretKey = Keys.hmacShaKeyFor(secret.getBytes("UTF-8"));
            Claims claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody();

            return claims;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserName(String token) {
        Claims claims = getClaims(token);
        if (claims == null) {
            return null;
        }
        return claims.getSubject();
    }

    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);
        if (claims == null) {
            return false;
        }
        String email = claims.getSubject();
        Date expirationDate = claims.getExpiration();
        Date now = new Date(System.currentTimeMillis());
        if (email != null && now.before(expirationDate)) {
            return true;
        }
        else {
            return false;
        }
    }

}