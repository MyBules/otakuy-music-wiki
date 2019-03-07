package com.otakuy.otakuymusic.util;

import com.otakuy.otakuymusic.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Value("${jjwt.secret}")
    private String secret;

    @Value("${jjwt.expirationTime}")
    private String expirationTime;

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    private Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        return doGenerateToken(claims, user);
    }

    private String doGenerateToken(Map<String, Object> claims, User user) {
        Long expirationTimeLong = Long.parseLong(expirationTime); //in second

        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);
        return Jwts.builder()
                .setIssuer("otakuy.com")
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

   /* public Integer getStar(String token) {
        return getAllClaimsFromToken(token.substring(7)).get("star", Integer.class);
    }*/

    public String getId(String token) {
        return getAllClaimsFromToken(token.substring(7)).get("id",String.class);
    }

    public String getUserName(String token) {
        return getAllClaimsFromToken(token.substring(7)).get("username", String.class);
    }

}
