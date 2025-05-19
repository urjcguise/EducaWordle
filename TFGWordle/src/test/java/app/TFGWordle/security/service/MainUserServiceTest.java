package app.TFGWordle.security.service;

import app.TFGWordle.security.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainUserServiceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private MainUserService mainUserService;

    @Test
    public void testLoadUserByUsernameUserFound() {
        String email = "user@email.com";
        String name = "user";
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword("password");

        when(userService.getByEmail(email)).thenReturn(Optional.of(user));

        UserDetails userDetails = mainUserService.loadUserByUsername(email);

        assertEquals(name, userDetails.getUsername());
        verify(userService, times(1)).getByEmail(email);
    }

    @Test
    public void testLoadUserByUsernameUserNotFound() {
        String email = "user@email.com";

        when(userService.getByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> mainUserService.loadUserByUsername(email));
        verify(userService, times(1)).getByEmail(email);
    }
}