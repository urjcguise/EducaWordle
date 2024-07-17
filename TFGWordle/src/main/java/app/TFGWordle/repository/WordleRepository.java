package app.TFGWordle.repository;

import app.TFGWordle.model.Wordle;
import org.springframework.data.repository.CrudRepository;

public interface WordleRepository extends CrudRepository<Wordle, Long> {
}
