package app.TFGWordle.repository;

import app.TFGWordle.model.ContestState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestStateRepository extends JpaRepository<ContestState, Long> {

    @Query("SELECT COUNT(e) > 0 FROM ContestState e WHERE e.contest.id = :contestId AND e.user.id = :userId")
    boolean existsByContestIdAndUserId(@Param("contestId") Long contestId, @Param("userId") Long userId);

    @Query(value = "SELECT state FROM contest_state WHERE contest_id = :contestId AND user_id = :userId", nativeQuery = true)
    String getState(@Param("contestId") Long contestId, @Param("userId") Long userId);

    @Query("SELECT cs FROM ContestState cs WHERE cs.contest.id = :contestId AND cs.user.id = :userId")
    ContestState findByContestIdAndUserId(@Param("contestId") Long contestId, @Param("userId") Long userId);

    @Query("SELECT cs FROM ContestState cs WHERE cs.contest.id = :contestId")
    List<ContestState> findByContestId(@Param("contestId") Long contestId);

    @Query("SELECT COUNT(e) > 0 FROM ContestState e WHERE e.contest.id = :contestId")
    boolean existsByContestId(@Param("contestId") Long contestId);
}
