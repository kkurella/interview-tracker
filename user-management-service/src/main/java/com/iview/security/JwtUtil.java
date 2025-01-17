package com.iview.security;


import com.iview.service.CustomUserDetailsService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "yoursecretkeyyoursecretkeyyoursecretkeyyoursecretkey";
    private final long EXPIRATION_TIME = 25 * 60 * 1000; // 25 minutes

    @Autowired
    CustomUserDetailsService userDetailsService;

    public String generateToken(String username) {

//        Map<String, Object> claims = Stream.of(new String[][]{
//                {"claim1", "ROLE_ADMIN"},
//                {"claim2", "ROLE_USER"}
//        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        return Jwts.builder()
                .setAudience("work-flow-status")
                .setHeaderParam("typ","JWT")
                .claim("roles", userDetailsService.loadUserByUsername(username).getAuthorities())
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String tokenUsername = extractClaims(token).getSubject();
        return (userDetails.getUsername().equals(tokenUsername) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
