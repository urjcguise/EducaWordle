package app.TFGWordle.repository;

import app.TFGWordle.model.DictionaryExternal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DictionaryExternalRepository extends JpaRepository<DictionaryExternal, Integer> {

    @Query("SELECT COUNT(d) > 0 FROM DictionaryExternal d WHERE LOWER(d.wordle) = LOWER(:wordle) AND d.contest.id = :contestId")
    boolean existsWord(@Param("wordle") String wordle, @Param("contestId") Long contestId);
}
