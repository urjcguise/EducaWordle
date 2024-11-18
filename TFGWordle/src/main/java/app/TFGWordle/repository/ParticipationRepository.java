package app.TFGWordle.repository;

import app.TFGWordle.model.Participation;
import app.TFGWordle.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    @Query("SELECT p.student FROM Participation p JOIN p.student.roles r WHERE p.competition.id = :competitionId AND r.rolName = 'ROLE_STUDENT'")
    List<User> findByCompetitionId(@Param("competitionId")Long competitionId);
}
