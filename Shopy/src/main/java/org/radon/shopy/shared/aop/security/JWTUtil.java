package org.radon.shopy.shared.aop.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static final String SECRET_KEY = "aljvnkjn3)n3u4nf9un9u3n59-3inon3v";

    private static final long ACCESS_TOKEN_VALIDITY_SECONDS = 1000 * 60 * 5; // 1 MIN
    private static final long REFRESH_TOKEN_VALIDITY_SECONDS = 1000 * 60 * 15; // 15 MIN


    public String generateToken(UserDetails userDetails) {
        return tokenGenerator(userDetails, System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return tokenGenerator(userDetails, System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY_SECONDS);
    }

    private String tokenGenerator(UserDetails userDetails,long expiration) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles",userDetails.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(expiration))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isExpired(token);
    }

    public boolean isExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }



}
