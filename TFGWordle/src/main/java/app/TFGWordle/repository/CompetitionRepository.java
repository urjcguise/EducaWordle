package app.TFGWordle.repository;

import app.TFGWordle.model.Competition;
import app.TFGWordle.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Optional<Competition> findByName(String name);
    Optional<Competition> findById(Long id);
    List<Competition> findByProfessor(User professor);
}
