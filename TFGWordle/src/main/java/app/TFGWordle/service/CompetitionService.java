package app.TFGWordle.service;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Participation;
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

    public Competition save(Competition competition) {
        return competitionRepository.save(competition);
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

    public List<Participation> getParticipations(Long id) {
        return competitionRepository.getParticipationsById(id);
    }
}
