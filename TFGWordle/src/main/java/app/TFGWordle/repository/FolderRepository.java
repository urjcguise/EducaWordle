package app.TFGWordle.repository;


import app.TFGWordle.model.Folder;
import app.TFGWordle.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findByProfessor(User professor);

    @Query("SELECT COUNT(f) > 0 FROM Folder f WHERE f.name = :name")
    boolean findByName(@Param("name") String name);

    Folder getByName(String name);
}
