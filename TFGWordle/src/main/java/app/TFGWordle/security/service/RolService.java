package app.TFGWordle.security.service;

import app.TFGWordle.security.entity.Rol;
import app.TFGWordle.security.enums.RolName;
import app.TFGWordle.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getRol(RolName rol) {
        return rolRepository.findByRolName(rol);
    }

    public void saveRol(Rol rol) {
        rolRepository.save(rol);
    }
}
