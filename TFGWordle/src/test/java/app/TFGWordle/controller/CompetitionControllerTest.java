package app.TFGWordle.controller;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Contest;
import app.TFGWordle.model.Participation;
import app.TFGWordle.security.entity.Rol;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.enums.RolName;
import app.TFGWordle.security.service.RolService;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.CompetitionService;
import app.TFGWordle.service.ContestService;
import app.TFGWordle.service.ParticipationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CompetitionControllerTest {

    private final String BASE_PATH = "/api/competitions";

    private final CompetitionService competitionService = mock(CompetitionService.class);

    private final ContestService contestService = mock(ContestService.class);

    private final UserService userService = mock(UserService.class);

    private final ParticipationService participationService = mock(ParticipationService.class);

    private final RolService rolService = mock(RolService.class);

    private final CompetitionController competitionController = new CompetitionController(competitionService, contestService, userService, participationService);

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(competitionController).build();


    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void createCompetitionSuccess() throws Exception {
        User professor = new User("user", "user@gmail.com", "password");

        when(userService.getByUserName(professor.getUsername())).thenReturn(Optional.of(professor));

        Competition competition = new Competition("competition", professor);
        when(competitionService.existsCompetitionByName(competition.getCompetitionName())).thenReturn(false);


        mockMvc.perform(post(BASE_PATH + "/newCompetition/" + professor.getUsername())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(competition)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void createCompetitionNoExistsUser() throws Exception {
        when(userService.getByUserName("user")).thenReturn(Optional.empty());
        mockMvc.perform(post(BASE_PATH + "/newCompetition/" + "user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new Competition())))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void createCompetitionSameName() throws Exception {
        Long id = 1L;
        User professor = new User("user", "user@gmail.com", "password");
        professor.setId(id);

        when(userService.getByUserName(professor.getUsername())).thenReturn(Optional.of(professor));

        Competition competition = new Competition("competition", professor);
        when(competitionService.existsCompetitionByNameAndProfesor(id, competition.getCompetitionName())).thenReturn(true);


        mockMvc.perform(post(BASE_PATH + "/newCompetition/" + professor.getUsername())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(competition)))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void editCompetitionSuccess() throws Exception {
        Long id = 1L;
        String oldName = "old name";
        String newName = "new name";

        Competition competition = new Competition();
        competition.setId(id);
        competition.setCompetitionName(oldName);

        when(competitionService.existsCompetition(id)).thenReturn(true);
        when(competitionService.getCompetitionById(id)).thenReturn(competition);

        mockMvc.perform(post(BASE_PATH + "/editCompetition/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(newName))
                .andExpect(status().isOk());
        verify(competitionService, times(1)).save(any(Competition.class));
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void editCompetitionNotFound() throws Exception {
        Long id = 1L;
        String newName = "new name";

        when(competitionService.existsCompetition(id)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/editCompetition/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newName))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void getCompetitionsByProfessorSuccess() throws Exception {
        User professor = new User("user", "user@gmail.com", "password");
        Competition competition = new Competition("competition", professor);

        when(userService.getByUserName(professor.getUsername())).thenReturn(Optional.of(professor));
        when(competitionService.getCompetitionsByProfesor(professor)).thenReturn(List.of(competition));

        mockMvc.perform(get(BASE_PATH + "/getCompetitions/" + professor.getUsername()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void getCompetitionsByProfessorNoExistsUser() throws Exception {
        when(userService.getByUserName("user")).thenReturn(Optional.empty());

        mockMvc.perform(get(BASE_PATH + "/getCompetitions/" + "user"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void deleteCompetitionSuccess() throws Exception {
        Long competitionId = 1L;
        Competition competition = new Competition("competition", new User());
        competition.setId(competitionId);

        User student1 = new User("student1", "student1@gmail.com", "password");
        User student2 = new User("student2", "student2@gmail.com", "password");

        Participation participation1 = new Participation(student1, competition);
        Participation participation2 = new Participation(student2, competition);

        student1.setParticipations(Set.of(participation1));
        student2.setParticipations(Set.of(participation2, new Participation(student2, new Competition())));

        List<Participation> participations = List.of(participation1, participation2);

        Contest contest1 = new Contest();
        contest1.setId(1L);
        Contest contest2 = new Contest();
        contest2.setId(2L);
        List<Contest> contests = List.of(contest1, contest2);

        when(competitionService.existsCompetition(competitionId)).thenReturn(true);
        when(competitionService.getParticipations(competitionId)).thenReturn(participations);
        when(competitionService.getCompetitionById(competitionId)).thenReturn(competition);
        when(contestService.getContestsByCompetition(competition)).thenReturn(contests);

        mockMvc.perform(delete(BASE_PATH + "/deleteCompetition/" + competitionId))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(student1);
        verify(userService, never()).deleteUser(student2);
        verify(contestService, times(1)).deleteContest(contests.get(0).getId());
        verify(contestService, times(1)).deleteContest(contests.get(1).getId());
        verify(competitionService, times(1)).deleteCompetition(competitionId);
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void deleteCompetition_NotFound() throws Exception {
        when(competitionService.existsCompetition(1l)).thenReturn(false);

        mockMvc.perform(delete(BASE_PATH + "/deleteCompetition/" + 1l))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void getStudentsSuccess() throws Exception {
        Long competitionId = 1L;
        User professor = new User("user", "user@gmail.com", "password");
        User student = new User("student", "student@gmail.com", "password");

        Competition competition = new Competition("competition", professor);
        Participation participation = new Participation(student, competition);
        HashSet<Participation> participations = new HashSet<Participation>();
        participations.add(participation);

        competition.setId(competitionId);
        competition.setParticipations(participations);

        when(competitionService.existsCompetition(competitionId)).thenReturn(true);
        when(participationService.findStudentsByCompetition(competitionId)).thenReturn(List.of(student));

        mockMvc.perform(get(BASE_PATH + "/getStudents/" + competitionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void getStudentsNotFound() throws Exception {
        Long competitionId = 1L;
        when(competitionService.existsCompetition(competitionId)).thenReturn(false);

        mockMvc.perform(get(BASE_PATH + "/getStudents/" + competitionId))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void linkStudentSuccess() throws Exception {
        Long competitionId = 1L;
        Long userId = 1L;

        User professor = new User("user", "user@gmail.com", "password");
        User student = new User("student", "student@gmail.com", "password");
        student.setId(userId);

        Competition competition = new Competition("competition", professor);
        competition.setId(competitionId);
        when(competitionService.existsCompetition(competitionId)).thenReturn(true);
        when(userService.existsById(userId)).thenReturn(true);

        when(competitionService.getCompetitionById(competitionId)).thenReturn(competition);
        when(userService.getById(userId)).thenReturn(Optional.of(student));

        mockMvc.perform(post(BASE_PATH + "/linkStudentToCompetition/" + competitionId + '/' + userId))
                .andExpect(status().isOk());

        verify(participationService, times(1)).save(any(Participation.class));
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void linkStudentCompetitionNotFound() throws Exception {
        Long competitionId = 1L;
        Long userId = 1L;

        when(competitionService.existsCompetition(competitionId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/linkStudentToCompetition/" + competitionId + '/' + userId))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void linkStudentStudentNotFound() throws Exception {
        Long competitionId = 1L;
        Long userId = 1L;

        when(userService.existsById(userId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/linkStudentToCompetition/" + competitionId + '/' + userId))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void addStudentsByExcelSuccess() throws Exception {
        Long competitionId = 1L;

        Competition competition = new Competition("competition", new User());
        competition.setId(competitionId);

        when(competitionService.existsCompetition(competitionId)).thenReturn(true);
        when(competitionService.getCompetitionById(competitionId)).thenReturn(competition);

        Workbook workbook = WorkbookFactory.create(true);
        Sheet sheet = workbook.createSheet("Students");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Email");
        headerRow.createCell(2).setCellValue("Password");

        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue("Test Student");
        dataRow.createCell(1).setCellValue("test@example.com");
        dataRow.createCell(2).setCellValue("password123");

        when(userService.existsByUserName("Test Student")).thenReturn(false);
        when(userService.existsByEmail("test@example.com")).thenReturn(false);
        when(rolService.getRol(RolName.ROLE_STUDENT)).thenReturn(Optional.of(new Rol(RolName.ROLE_STUDENT)));

        byte[] excelBytes;
        try (java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream()) {
            workbook.write(outputStream);
            excelBytes = outputStream.toByteArray();
        }

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "students.xlsx",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                excelBytes
        );

        mockMvc.perform(post(BASE_PATH + "/addStudentsByExcel/" + competitionId)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(String.valueOf(file)))
                .andExpect(status().isOk());

        verify(userService, times(1)).save(any(User.class));
        verify(participationService, times(1)).save(any(Participation.class));
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "PROFESSOR"})
    void addStudentsByExcelNotFound() throws Exception {
        Long competitionId = 1L;

        when(competitionService.existsCompetition(competitionId)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/addStudentsByExcel/" + competitionId))
                .andExpect(status().isNotFound());
    }


}