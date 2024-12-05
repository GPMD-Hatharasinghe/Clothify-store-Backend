package edu.icet.ClothifyStor.security;

import edu.icet.ClothifyStor.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JwtUtil {
    private final String SECRET_KEY = "cLOTHIFYcreatedBYmrdillGPfromIUHS01BatchINgalleforICET";

    public String checkToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }
        String actualToken = token.substring(7);

        try {
            String email = extractEmail(actualToken);

            log.info("Extracted email: {}", email);
            return email;

        } catch (Exception e) {
            log.error("Token validation failed", e);
            return null;
        }
    }




    public boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // Use parserBuilder() for the latest version
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }


}
