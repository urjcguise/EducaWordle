package app.TFGWordle.repository;

import app.TFGWordle.model.Wordle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordleRepository extends JpaRepository<Wordle, Long> {
    Wordle findByWord(String word);
    void deleteByContestId(Long id);

    List<Wordle> findByContestId(Long id);
}
