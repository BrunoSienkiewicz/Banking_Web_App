package pl.Portfolio.bankingapp.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import pl.Portfolio.bankingapp.Model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class TokenService {
    private String secretKey = "FEF##(T_#%FRFA";
    public String generateToken(User user) {
        long expirationTime = 3600000; // 1 hour

        Map<String, Object> claims = new HashMap<>();

        if (Objects.equals(user.getRole(), "USER")) {
            claims.put("isUser", true);
        }
        if (Objects.equals(user.getRole(), "ADMIN")) {
            claims.put("isAdmin", true);
        }

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return token;
    }

    public String getUsernameFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return username;
    }

    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        if(claims.containsKey("isAdmin"))
            return "ADMIN";
        else if (claims.containsKey("isUser")) {
            return "USER";
        }
        return null;
    }
}
