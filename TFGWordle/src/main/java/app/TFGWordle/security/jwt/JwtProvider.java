package app.TFGWordle.security.jwt;

import app.TFGWordle.security.entity.MainUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication auth) {
        MainUser mainUser = (MainUser) auth.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", mainUser.getUsername());
        claims.put("roles", mainUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(mainUser.getEmail())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000L))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado", e);
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado", e);
        } catch (IllegalArgumentException e) {
            logger.error("Token vacío", e);
        } catch (SignatureException e) {
            logger.error("Fail en la firma", e);
        }
        return false;
    }

}
