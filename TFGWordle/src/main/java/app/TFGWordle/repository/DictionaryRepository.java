package app.TFGWordle.repository;

import app.TFGWordle.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

    @Query("SELECT COUNT(d) > 0 FROM Dictionary d WHERE LOWER(d.wordle) = LOWER(:wordle)")
    Boolean existsWordle(String wordle);
}
