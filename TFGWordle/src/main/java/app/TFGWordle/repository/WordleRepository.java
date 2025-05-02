package app.TFGWordle.repository;

import app.TFGWordle.model.Wordle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WordleRepository extends JpaRepository<Wordle, Long> {
    Optional<Wordle> findByWord(String word);
    List<Wordle> findByContestsId(Long id);
    List<Wordle> findByUserId(Long id);
}
