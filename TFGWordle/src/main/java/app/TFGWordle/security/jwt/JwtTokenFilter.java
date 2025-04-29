package app.TFGWordle.security.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(req);
            if (token != null) {
                if (jwtProvider.validateToken(token)) {
                    logger.info("Token válido, autenticando...");
                    //logger.info("Obteniendo nombre de usuario del token...");
                    String email = jwtProvider.getEmailFromToken(token);
                    //logger.info("Nombre de usuario obtenido: " + userName);

                    //logger.info("Cargando detalles del usuario...");
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                    /*
                    for (GrantedAuthority authority : userDetails.getAuthorities()) {
                        String authority1 = authority.getAuthority();
                        logger.info("Autenticando: {}", authority1);
                    }*/

                    //logger.info("Detalles del usuario cargados.");

                    //logger.info("Creando objeto de autenticación...");
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    //logger.info("Autenticación creada.");

                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    logger.warn("Token no válido o expirado.");
                }
            } else {
                logger.info("No se encontró token, permitiendo acceso sin autenticación.");
            }
        } catch (Exception e) {
            logger.error("Error en el doFilterInternal", e);
        }

        filterChain.doFilter(req, res);
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {
            return header.replace("Bearer ", "");
        }
        return null;
    }
}
