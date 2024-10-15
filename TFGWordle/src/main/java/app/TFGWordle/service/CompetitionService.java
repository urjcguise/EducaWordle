package app.TFGWordle.service;

import app.TFGWordle.model.Competition;
import app.TFGWordle.repository.CompetitionRepository;
import app.TFGWordle.security.entity.Rol;
import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.enums.RolName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    public Competition save(Competition competition, User professor) {
        if (isProfessor(professor)) {
            return competitionRepository.save(competition);
        } else {
            throw new RuntimeException("El usuario no tiene permisos para crear la competición");
        }
    }



    private boolean isProfessor(User user) {
        for (Rol rol : user.getRoles()) {
            if (rol.getRolName().name().equals("ROL_PROFESSOR"))
                return true;
        }
        return false;
    }

    public List<Competition> getCompetitionsByProfesor(User professor) {
        return competitionRepository.findByProfessor(professor);
    }
}
