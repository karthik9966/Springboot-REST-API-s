package com.ttu.blogapplication.utils;

import com.ttu.blogapplication.exception.BlogApplicationException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider{

    @Value("${app.jwt-secret-key}")
    private String secret_key;

    @Value("${app.jwt-expiry-msec}")
    private long expiry;

    public String generateToken(Authentication authentication)
    {
        String subject = authentication.getName();
        Date currentDate = new Date();

        Date expiryDate = new Date(currentDate.getTime() + expiry);

        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(getKey())
                .compact();
        return token;
    }

    private Key getKey()
    {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret_key)
        );
    }

    public String getUsername(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token)
    {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parse(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new BlogApplicationException("Jwt token is expired", HttpStatus.BAD_REQUEST);
        } catch (MalformedJwtException e) {
            throw new BlogApplicationException("Invalid Jwt token",HttpStatus.BAD_REQUEST);
        } catch (SignatureException e) {
            throw new BlogApplicationException("Signature is incorrect",HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            throw new BlogApplicationException("JWT claims string is empty",HttpStatus.BAD_REQUEST);
        }
    }
}
