package app.TFGWordle.security.service;

import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetByUserNameSuccess() {
        User user = new User("user", "user@gmail.com", "password");

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getByUserName("user");

        assertTrue(result.isPresent());
        assertEquals("user", result.get().getUsername());
        assertEquals("user@gmail.com", result.get().getEmail());

        verify(userRepository, times(1)).findByUsername("user");
    }

    @Test
    void testGetByUserNameEmpty() {
        when(userRepository.findByUsername("user")).thenReturn(Optional.empty());

        Optional<User> result = userService.getByUserName("user");

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetByIdSuccess() {
        User user = new User("user", "user@gmail.com", "password");
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("user", result.get().getUsername());
        assertEquals("user@gmail.com", result.get().getEmail());

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetByIdEmpty() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> result = userService.getById(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetByEmailSuccess() {
        User user = new User("user", "user@gmail.com", "password");

        when(userRepository.findByEmail("user@gmail.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getByEmail("user@gmail.com");

        assertTrue(result.isPresent());
        assertEquals("user", result.get().getUsername());
        assertEquals("user@gmail.com", result.get().getEmail());

        verify(userRepository, times(1)).findByEmail("user@gmail.com");
    }

    @Test
    void testGetByEmailFail() {
        when(userRepository.findByEmail("user@gmail.com")).thenReturn(Optional.empty());

        Optional<User> result = userService.getByEmail("user@gmail.com");

        assertTrue(result.isEmpty());
    }

    @Test
    void testExistsByUserNameTrue() {
        User user = new User("user", "user@gmail.com", "password");

        when(userRepository.existsByUsername("user")).thenReturn(true);

        assertTrue(userRepository.existsByUsername(user.getUsername()));
    }

    @Test
    void testExistsByUserNameFalse() {
        when(userRepository.existsByUsername("user")).thenReturn(false);

        assertFalse(userRepository.existsByUsername("user"));
    }

    @Test
    void testExistsByEmailTrue() {
        User user = new User("user", "user@gmail.com", "password");

        when(userRepository.existsByEmail("user@gmail.com")).thenReturn(true);

        assertTrue(userRepository.existsByEmail(user.getEmail()));
    }

    @Test
    void testExistsByEmailFalse() {
        when(userRepository.existsByEmail("user@gmail.com")).thenReturn(false);

        assertFalse(userRepository.existsByEmail("user@gmail.com"));
    }

    @Test
    void testExistsByIdTrue() {
        User user = new User("user", "user@gmail.com", "password");
        user.setId(1L);

        when(userRepository.existsById(1L)).thenReturn(true);

        assertTrue(userRepository.existsById(user.getId()));
    }

    @Test
    void testExistsByIdFalse() {
        when(userRepository.existsById(1L)).thenReturn(false);

        assertFalse(userRepository.existsById(1L));
    }

    @Test
    void testSave() {
        User newUser = new User("user", "user@gmail.com", "password");

        userService.save(newUser);

        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void testGetAll() {
        User user = new User("user", "user@gmail.com", "password");

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<User> result = userService.getAll();

        assertEquals(1, result.size());
        assertEquals("user", result.get(0).getUsername());
    }

    @Test
    void testGetAllEmpty() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> result = userService.getAll();

        assertEquals(0, result.size());
    }

    @Test
    void testDeleteUser() {
        User newUser = new User("user", "user@gmail.com", "password");

        userService.deleteUser(newUser);

        verify(userRepository, times(1)).delete(newUser);
    }

}