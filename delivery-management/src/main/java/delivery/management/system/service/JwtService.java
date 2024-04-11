package delivery.management.system.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


public interface JwtService {

    Claims extractAllClaims(String jwt);

    <T> T extractClaim(String jwt, Function<Claims, T> claimsResolved);

    String extractUsername(String jwt);


    Date extractExpiration(String jwt);

    Key getSiginKey();


    String generateToken(String username,long userId, Map<String, Object> extraClaims);

    String buildToken(String username, Map<String, Object> extractClaims, long expiration, long id);

    boolean isTokenValid(String jwt);

    List<? extends GrantedAuthority> extractAuthorities(String jwt);
}

