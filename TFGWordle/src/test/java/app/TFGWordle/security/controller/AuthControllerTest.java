package app.TFGWordle.security.controller;

import app.TFGWordle.security.dto.LoginUser;
import app.TFGWordle.security.dto.NewUser;
import app.TFGWordle.security.entity.Rol;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.enums.RolName;
import app.TFGWordle.security.jwt.JwtProvider;
import app.TFGWordle.security.service.RolService;
import app.TFGWordle.security.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserService userService;

    @Mock
    private RolService rolService;

    @Mock
    private JwtProvider jwtProvider;

    @InjectMocks
    private AuthController authController;

    @Mock
    private BindingResult bindingResult;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void loginValidCredentials() throws Exception {
        LoginUser loginUser = new LoginUser();
        loginUser.setEmail("user@email.com");
        loginUser.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtProvider.generateToken(authentication)).thenReturn("testJwtToken");
        when(userDetails.getUsername()).thenReturn("testUser");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("testJwtToken"))
                .andExpect(jsonPath("$.userName").value("testUser"));

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtProvider, times(1)).generateToken(authentication);
    }

    @Test
    public void loginInvalidCredentials() throws Exception {
        LoginUser loginUser = new LoginUser();
        loginUser.setEmail("user@email.com");
        loginUser.setPassword("wrongPassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new org.springframework.security.authentication.BadCredentialsException("Bad credentials"));

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUser)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Campos mal puestos"));

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtProvider, never()).generateToken(any());
    }

    @Test
    public void newUserValidUser() throws Exception {
        NewUser newUser = new NewUser();
        newUser.setName("newUser");
        newUser.setEmail("newUser@example.com");
        newUser.setPassword("password");
        newUser.setRoles(Set.of("student"));

        Rol rol = new Rol();
        rol.setRolName(RolName.ROLE_STUDENT);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.existsByUserName("newUser")).thenReturn(false);
        when(userService.existsByEmail("newUser@example.com")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(rolService.getRol(RolName.ROLE_STUDENT)).thenReturn(Optional.of(rol));

        mockMvc.perform(post("/auth/newUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated());

        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    public void newUserUserNameExists() throws Exception {
        Long userId = 1L;
        NewUser newUser = new NewUser();
        newUser.setName("newUser");
        newUser.setEmail("newUser@example.com");
        newUser.setPassword("password");
        newUser.setRoles(Set.of("student"));

        User user = new User();
        user.setId(userId);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.existsByUserName("newUser")).thenReturn(true);
        when(userService.getByUserName("newUser")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/auth/newUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isConflict())
                .andExpect(content().string(String.valueOf(userId)));

        verify(userService, never()).save(any(User.class));
    }

    @Test
    public void newUserEmailExists() throws Exception {
        Long userId = 1L;
        NewUser newUser = new NewUser();
        newUser.setName("newUser");
        newUser.setEmail("newUser@example.com");
        newUser.setPassword("password");
        newUser.setRoles(Set.of("student"));

        User user = new User();
        user.setId(userId);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.existsByEmail("newUser@example.com")).thenReturn(true);
        when(userService.getByUserName("newUser")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/auth/newUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isConflict())
                .andExpect(content().string(String.valueOf(userId)));

        verify(userService, never()).save(any(User.class));
    }

}