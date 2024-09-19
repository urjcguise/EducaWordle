package app.TFGWordle.security.repository;

import app.TFGWordle.security.entity.Rol;
import app.TFGWordle.security.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRolName(RolName rolName);
}
