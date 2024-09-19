package app.TFGWordle.security.jwt;

import app.TFGWordle.security.entity.MainUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication auth) {
        MainUser mainUser = (MainUser) auth.getPrincipal();
        return Jwts.builder().setSubject(mainUser.getName())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000L))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUserNameFromToken(String token) {
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
