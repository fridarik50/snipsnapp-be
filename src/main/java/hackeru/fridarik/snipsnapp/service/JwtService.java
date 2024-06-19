package hackeru.fridarik.snipsnapp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SERVER_KEY = "iphone123sandroid3453nokiakotage989wlakiTtalki";
    private static final long TOKEN_EXPIRATION = 1000 *60 * 60;

    public String createToken(String email){
        Map<String, Object> payload = new HashMap<>();
        return createToken(payload, email);
    }
    public String createToken(Map<String, Object> payload, String email){
        return Jwts.builder()
                .setClaims(payload)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+TOKEN_EXPIRATION))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean validateToken(String token, UserDetails user){
        String email = extractEmail(token);
        return email.equals(user.getUsername()) && !isExpired(token);
    }
    public String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private boolean isExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    // helpers

    private Key getKey(){
        byte[] bytes = Decoders.BASE64.decode((SERVER_KEY));
        return Keys.hmacShaKeyFor(bytes);
    }
}
