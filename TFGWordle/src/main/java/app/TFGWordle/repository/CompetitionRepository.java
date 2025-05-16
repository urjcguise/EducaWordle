package app.TFGWordle.repository;

import app.TFGWordle.model.Competition;
import app.TFGWordle.model.Participation;
import app.TFGWordle.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Optional<Competition> findByName(String name);

    Optional<Competition> findById(Long id);

    List<Competition> findByProfessor(User professor);

    boolean existsByName(String name);

    @Query("SELECT c.participations FROM Competition c WHERE c.id = :id")
    List<Participation> getParticipationsById(@Param("id") Long id);

    @Query("SELECT COUNT(c) > 0 FROM Competition c WHERE c.professor.id = :professorId AND c.name = :competitionName")
    boolean existsByNameAndProfesor(@Param("professorId") Long professorId,
            @Param("competitionName") String competitionName);
}
