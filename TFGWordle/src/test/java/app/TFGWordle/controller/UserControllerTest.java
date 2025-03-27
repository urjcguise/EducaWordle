package app.TFGWordle.controller;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Participation;
import app.TFGWordle.security.dto.NewUser;
import app.TFGWordle.security.entity.Rol;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.enums.RolName;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.ParticipationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private final String BASE_PATH = "/api/users";

    private final UserService userService = mock(UserService.class);

    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    private final ParticipationService participationService = mock(ParticipationService.class);

    private final UserController userController = new UserController(passwordEncoder, userService, participationService);

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getAllProfessorsSuccess() throws Exception {
        Rol rolProfessor = new Rol(RolName.ROLE_PROFESSOR);
        Set<Rol> professors = new HashSet<>();
        professors.add(rolProfessor);
        User user1 = new User("user1", "user1@gmail.com", "password");
        user1.setRoles(professors);
        User user2 = new User("user2", "user2@gmail.com", "password");
        user2.setRoles(professors);
        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAll()).thenReturn(users);

        mockMvc.perform(get(BASE_PATH + "/getAllProfessors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(users.size()));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getAllStudentsSuccess() throws Exception {
        Rol rolStudent = new Rol(RolName.ROLE_STUDENT);
        Set<Rol> students = new HashSet<>();
        students.add(rolStudent);
        User user1 = new User("user1", "user1@gmail.com", "password");
        user1.setRoles(students);
        User user2 = new User("user2", "user2@gmail.com", "password");
        user2.setRoles(students);
        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAll()).thenReturn(users);

        mockMvc.perform(get(BASE_PATH + "/getAllStudents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(users.size()));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getUserDataSuccess() throws Exception {
        User user = new User("user", "user@gmail.com", "password");
        when(userService.getByUserName("user")).thenReturn(Optional.of(user));

        mockMvc.perform(get(BASE_PATH + "/getUserData/" + user.getUsername()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user.getUsername()));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getUserDataFail() throws Exception {
        when(userService.getByUserName("user")).thenReturn(Optional.empty());
        mockMvc.perform(get(BASE_PATH + "/getUserData/user"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void updateUserSuccess() throws Exception {
        String oldUserName = "oldUser";
        NewUser uploadUser = new NewUser();
        uploadUser.setName("newUser");
        uploadUser.setEmail("newUser@example.com");
        uploadUser.setPassword("newPassword");

        User userToUpdate = new User();
        userToUpdate.setUsername(oldUserName);
        userToUpdate.setEmail("oldUser@example.com");

        when(userService.getByUserName(oldUserName)).thenReturn(Optional.of(userToUpdate));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedPassword");

        mockMvc.perform(post(BASE_PATH + "/updateUser/" + oldUserName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(uploadUser)))
                .andExpect(status().isOk());

        verify(userService, times(1)).getByUserName(oldUserName);
        verify(userService, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode("newPassword");
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void updateUserFail() throws Exception {
        when(userService.getByUserName("user")).thenReturn(Optional.empty());

        NewUser updatedUser = new NewUser();
        updatedUser.setName("newUser");
        updatedUser.setEmail("new@example.com");
        updatedUser.setPassword("newPassword");

        mockMvc.perform(post("/updateUser/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedUser)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"STUDENT"})
    void getUserEmailSuccess() throws Exception {
        User user = new User("user", "user@gmail.com", "password");
        when(userService.getByUserName("user")).thenReturn(Optional.of(user));
        mockMvc.perform(get(BASE_PATH + "/getUserEmail/" + user.getUsername()))
                .andExpect(status().isOk())
                .andExpect(content().string("user@gmail.com"));
    }

    @Test
    @WithMockUser(roles = {"STUDENT"})
    void getUserEmailFail() throws Exception {
        when(userService.getByUserName("user")).thenReturn(Optional.empty());
        mockMvc.perform(get(BASE_PATH + "/getUserEmail/user"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteUserByNameSuccess() throws Exception {
        User user = new User("user", "user@gmail.com", "password");
        when(userService.getByUserName("user")).thenReturn(Optional.of(user));
        doNothing().when(userService).deleteUser(user);
        mockMvc.perform(delete(BASE_PATH + "/deleteUserByName/" + user.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteUserByNameFail() throws Exception {
        when(userService.getByUserName("user")).thenReturn(Optional.empty());
        mockMvc.perform(delete(BASE_PATH + "/deleteUserByName/user"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"STUDENT"})
    void getCompetitionSuccess() throws Exception {
        Rol rolProfessor = new Rol(RolName.ROLE_PROFESSOR);
        Set<Rol> professors = new HashSet<>();
        professors.add(rolProfessor);
        User professor = new User("professor", "professor@gmail.com", "password");
        User user = new User("user", "user@gmail.com", "password");

        Competition competition = new Competition("Competition", professor);

        Participation participation = new Participation(user, competition);

        when(userService.getByUserName("user")).thenReturn(Optional.of(user));
        when(participationService.findParticipationsByStudent(user.getId())).thenReturn(List.of(participation));

        mockMvc.perform(get(BASE_PATH + "/getCompetitions/" + user.getUsername()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    @WithMockUser(roles = {"STUDENT"})
    void getCompetitionFail() throws Exception {
        when(userService.getByUserName("user")).thenReturn(Optional.empty());
        mockMvc.perform(get(BASE_PATH + "/getCompetitions/user"))
                .andExpect(status().isNotFound());
    }
}