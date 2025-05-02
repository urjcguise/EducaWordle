package app.TFGWordle.controller;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Contest;
import app.TFGWordle.model.Participation;
import app.TFGWordle.security.entity.Rol;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.enums.RolName;
import app.TFGWordle.security.jwt.JwtTokenFilter;
import app.TFGWordle.security.service.RolService;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.CompetitionService;
import app.TFGWordle.service.ContestService;
import app.TFGWordle.service.ParticipationService;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/api/competitions")
@CrossOrigin
public class CompetitionController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolService rolService;

    private final CompetitionService competitionService;
    private final ContestService contestService;
    private final UserService userService;
    private final ParticipationService participationService;

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    public CompetitionController(CompetitionService competitionService, ContestService contestService, UserService userService, ParticipationService participationService) {
        this.competitionService = competitionService;
        this.contestService = contestService;
        this.userService = userService;
        this.participationService = participationService;
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/newCompetition/{professorName}")
    public ResponseEntity<?> createCompetition(@PathVariable String professorName, @RequestBody Competition competition) {
        User professor = userService.getByUserName(professorName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (competitionService.existsCompetitionByName(competition.getCompetitionName()))
            return new ResponseEntity<>("Nombre ya utilizado", HttpStatus.CONFLICT);

        competition.setProfessor(professor);
        competitionService.save(competition);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/editCompetition/{competitionId}")
    public ResponseEntity<?> editCompetition(@PathVariable Long competitionId, @RequestBody String newName) {
        if (!competitionService.existsCompetition(competitionId))
            return new ResponseEntity<>("Competición no encontrada", HttpStatus.NOT_FOUND);


        Competition competition = competitionService.getCompetitionById(competitionId);
        competition.setCompetitionName(newName);
        competitionService.save(competition);

        return new ResponseEntity<>(HttpStatus.OK );
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @GetMapping("/getCompetitions/{professorName}")
    public ResponseEntity<List<Competition>> getCompetitionsByProfessor(@PathVariable String professorName) {
        User professor = userService.getByUserName(professorName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<Competition> competitions = competitionService.getCompetitionsByProfesor(professor);
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @DeleteMapping("/deleteCompetition/{competitionId}")
    public ResponseEntity<?> deleteCompetition(@PathVariable Long competitionId) {
        if (!competitionService.existsCompetition(competitionId))
            return new ResponseEntity<>("Competición no encontrada", HttpStatus.NOT_FOUND);

        List<Participation> participations = competitionService.getParticipations(competitionId);
        for (Participation p: participations) {
            User student = p.getStudent();
            if (student.getParticipations().size() == 1)
                userService.deleteUser(student);
        }

        List<Contest> contestInCompetition = contestService.getContestsByCompetition(competitionService.getCompetitionById(competitionId));
        for (Contest contest : contestInCompetition) {
            contestService.deleteContest(contest.getId());
        }
        competitionService.deleteCompetition(competitionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getStudents/{competitionName}")
    public ResponseEntity<List<User>> getStudents(@PathVariable String competitionName) {
        return competitionService.existsCompetitionByName(competitionName)
                ? new ResponseEntity<>(participationService.findStudentsByCompetition(competitionName), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/linkStudentToCompetition/{competitionId}/{userId}")
    public ResponseEntity<?> linkStudent(@PathVariable Long competitionId, @PathVariable Long userId ) {
        if (competitionService.existsCompetition(competitionId) && userService.existsById(userId)) {
            Competition competition = competitionService.getCompetitionById(competitionId);
            User user = userService.getById(userId).get();
            Participation newParticipation = new Participation(user, competition);

            competition.getParticipations().add(newParticipation);
            user.getParticipations().add(newParticipation);

            participationService.save(newParticipation);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ADMIN')")
    @PostMapping("/addStudentsByExcel/{competitionId}")
    public ResponseEntity<?> addStudentsByExcel(@PathVariable Long competitionId, @RequestBody MultipartFile file) {
        if (competitionService.existsCompetition(competitionId)) {
            try (InputStream inputStream = file.getInputStream()) {
                Competition competition = competitionService.getCompetitionById(competitionId);

                Workbook workbook = WorkbookFactory.create(inputStream);
                Sheet sheet = workbook.getSheetAt(0);

                for (Row row : sheet) {
                    if (row.getRowNum() == 0) continue;
                    if (isRowEmpty(row)) continue;

                    String name = row.getCell(0).getStringCellValue();
                    String email = row.getCell(1).getStringCellValue();
                    String password = row.getCell(2).getStringCellValue();

                    if (userService.existsByUserName(name) || userService.existsByEmail(email)) {
                        User userExists = userService.getByUserName(name).get();
                        List<Participation> participations = participationService.findParticipationsByStudent(userExists.getId());
                        if (!participations.contains(competition)) {
                            Participation newParticipation = new Participation(userExists, competition);
                            participationService.save(newParticipation);
                        }
                        continue;
                    }

                    User newUser = new User(name, email, passwordEncoder.encode(password));

                    Set<Rol> roles = new HashSet<>();
                    roles.add(rolService.getRol(RolName.ROLE_STUDENT).get());
                    newUser.setRoles(roles);

                    Participation participation = new Participation(newUser, competition);
                    newUser.getParticipations().add(participation);
                    competition.getParticipations().add(participation);

                    userService.save(newUser);
                    participationService.save(participation);
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private boolean isRowEmpty(Row row) {
        for (int i = 0; i < 3; i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}
