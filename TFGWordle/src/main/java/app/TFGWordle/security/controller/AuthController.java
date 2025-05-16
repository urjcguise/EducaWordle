package app.TFGWordle.security.controller;

import app.TFGWordle.security.dto.JwtDto;
import app.TFGWordle.security.dto.LoginUser;
import app.TFGWordle.security.dto.NewUser;
import app.TFGWordle.security.entity.Rol;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.enums.RolName;
import app.TFGWordle.security.jwt.JwtProvider;
import app.TFGWordle.security.jwt.JwtTokenFilter;
import app.TFGWordle.security.service.RolService;
import app.TFGWordle.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity("Campos mal puestos", HttpStatus.BAD_REQUEST);

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
            return new ResponseEntity<>(jwtDto, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity("Campos mal puestos", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/newUser")
    public ResponseEntity<?> newUser(@RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>("Campos mal puestos o email inválido", HttpStatus.BAD_REQUEST);

        if (userService.existsByUserName(newUser.getName()) || userService.existsByEmail(newUser.getEmail())) {
            User existingUser = userService.getByUserName(newUser.getName()).get();
            return new ResponseEntity<>(existingUser.getId(), HttpStatus.CONFLICT);
        }

        User user = new User(newUser.getName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));

        Set<Rol> roles = new HashSet<>();
        for (String rolName : newUser.getRoles()) {
            logger.info(rolName);
            if (Objects.equals(rolName, "admin") && rolService.getRol(RolName.ROLE_ADMIN).isPresent()) {
                roles.add(rolService.getRol(RolName.ROLE_ADMIN).get());
            }
            if (Objects.equals(rolName, "professor") && rolService.getRol(RolName.ROLE_PROFESSOR).isPresent()) {
                roles.add(rolService.getRol(RolName.ROLE_PROFESSOR).get());
            }
            if (Objects.equals(rolName, "student") && rolService.getRol(RolName.ROLE_STUDENT).isPresent()) {
                roles.add(rolService.getRol(RolName.ROLE_STUDENT).get());
            }
        }

        user.setRoles(roles);
        userService.save(user);

        return new ResponseEntity<>(user.getId(), HttpStatus.CREATED);
    }
}
