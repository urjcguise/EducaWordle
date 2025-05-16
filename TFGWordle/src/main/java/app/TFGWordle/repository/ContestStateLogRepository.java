package app.TFGWordle.repository;

import app.TFGWordle.model.ContestStateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContestStateLogRepository extends JpaRepository<ContestStateLog, Long> {

    @Query("SELECT csl FROM ContestStateLog csl WHERE csl.contest.id = :contestId")
    List<ContestStateLog> findByContestId(@Param("contestId") Long contestId);

    @Query("SELECT csl FROM ContestStateLog csl WHERE csl.contest.id = :contestId AND csl.user.id = :userId")
    List<ContestStateLog> findByContestIdAndUserId(@Param("contestId") Long contestId, @Param("userId") Long userId);

    @Query("SELECT csl.state FROM ContestStateLog csl WHERE csl.contest.id = :contestId")
    List<String> findAllStateLogs(@Param("contestId") Long contestId);

    @Query("SELECT csl.state FROM ContestStateLog csl WHERE csl.contest.id = :contestId AND csl.user.id = :userId")
    List<String> findAllLogsByContestIdAndUserId(@Param("contestId") Long contestId, @Param("userId") Long userId);
}
