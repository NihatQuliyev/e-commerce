package delivery.management.system.service.impl;

import delivery.management.system.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class IJwtService implements JwtService {

    @Value("${application.security.secret-key}")
    String secretKey;

    @Value("${application.security.access-token-expiration}")
    Long accessTokenExpiration;

    @Value("${application.security.refresh-token-expiration}")
    Long refreshTokenExpiration;

    @Override
    public Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(getSiginKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();

    }

    @Override
    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolved) {

        return claimsResolved.apply(extractAllClaims(jwt));
    }

    @Override
    public String extractUsername(String jwt) {

        return extractClaim(jwt, Claims::getSubject);
    }

    @Override
    public Date extractExpiration(String jwt) {

        return extractClaim(jwt, Claims::getExpiration);
    }

    @Override
    public Key getSiginKey() {

        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    @Override
    public String generateToken(String userDetails, long userId, Map<String, Object> extractClaims) {

        return buildToken(userDetails, extractClaims, accessTokenExpiration, userId);
    }


    @Override
    public String buildToken(String username, Map<String, Object> extractClaims, long expiration, long userId) {
        return Jwts.builder()
                .setClaims(extractClaims)
                .addClaims(extractClaims)
                .setSubject(username)
                .setId(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String jwt) {
        return !isTokenExpired(jwt);
    }

    @Override
    public List< ? extends GrantedAuthority > extractAuthorities(String jwt) {
        return  ((List<String>) extractAllClaims(jwt).get("authorities")).stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }


    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }



}
