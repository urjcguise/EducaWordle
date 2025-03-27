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
    public void testLoadUserByUsername_UserFound() {
        // Arrange
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");

        when(userService.getByUserName(username)).thenReturn(Optional.of(user));

        UserDetails userDetails = mainUserService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        verify(userService, times(1)).getByUserName(username);
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        String username = "nonexistentUser";

        when(userService.getByUserName(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> mainUserService.loadUserByUsername(username));
        verify(userService, times(1)).getByUserName(username);
    }
}