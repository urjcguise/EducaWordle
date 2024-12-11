package app.TFGWordle.controller;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Participation;
import app.TFGWordle.security.entity.Rol;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.enums.RolName;
import app.TFGWordle.security.service.RolService;
import app.TFGWordle.security.service.UserService;
import app.TFGWordle.service.CompetitionService;
import app.TFGWordle.service.ParticipationService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolService rolService;

    private final UserService userService;
    private final ParticipationService participationService;
    private final CompetitionService competitionService;

    public UserController(UserService userService, ParticipationService participationService, CompetitionService competitionService) {
        this.userService = userService;
        this.participationService = participationService;
        this.competitionService = competitionService;
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/getCompetitions/{userName}")
    public ResponseEntity<List<Competition>> getCompetition(@PathVariable String userName) {
        if (userService.existsByUserName(userName)) {
            User user = userService.getByUserName(userName).get();
            List<Competition> toReturn = new ArrayList<>();
            for (Participation participation : participationService.findCompetitionsByStudent(user.getId())) {
                toReturn.add(participation.getCompetition());
            }
            return new ResponseEntity<>(toReturn, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('PROFESSOR')")
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
                return new ResponseEntity<>(Map.of("message", "Archivo correctamente procesado"), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Competición no encontrada", HttpStatus.NOT_FOUND);
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
