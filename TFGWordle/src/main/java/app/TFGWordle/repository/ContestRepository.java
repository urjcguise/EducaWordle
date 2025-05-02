package app.TFGWordle.repository;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestRepository extends JpaRepository<Contest, Long> {
    List<Contest> findByCompetition(Competition competition);
}
