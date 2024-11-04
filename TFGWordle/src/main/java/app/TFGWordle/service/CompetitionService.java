package app.TFGWordle.service;

import app.TFGWordle.model.Competition;
import app.TFGWordle.repository.CompetitionRepository;
import app.TFGWordle.security.entity.Rol;
import app.TFGWordle.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
            if (rol.getRolName().name().equals("ROLE_PROFESSOR"))
                return true;
        }
        return false;
    }

    public List<Competition> getCompetitionsByProfesor(User professor) {
        return competitionRepository.findByProfessor(professor);

    }

    public Competition getCompetitionByName(String name) {
        return competitionRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("Competicion con nombre: " + name + " no existe"));
    }

    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id).orElse(null);
    }

    public void deleteCompetition(Long id) {
        competitionRepository.deleteById(id);
    }

    public boolean existsCompetition(Long id) {
        return competitionRepository.existsById(id);
    }

    public boolean existsCompetitionByName(String name) {
        return competitionRepository.existsByName(name);
    }
}
