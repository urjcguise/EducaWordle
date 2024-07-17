package app.TFGWordle.repository;

import app.TFGWordle.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}
