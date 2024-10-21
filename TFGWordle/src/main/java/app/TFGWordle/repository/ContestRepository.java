package app.TFGWordle.repository;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContestRepository extends JpaRepository<Contest, Long> {
    Optional<Contest> findByName(String name);
    List<Contest> findByCompetition(Competition competition);
}
