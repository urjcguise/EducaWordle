package app.TFGWordle.security.util;

import app.TFGWordle.security.entity.Rol;
import app.TFGWordle.security.enums.RolName;
import app.TFGWordle.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRol implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
//        Rol rolAdmin = new Rol(RolName.ROL_ADMIN);
//        Rol rolProfessor = new Rol(RolName.ROL_PROFESSOR);
//        rolService.saveRol(rolAdmin);
//        rolService.saveRol(rolProfessor);
    }
}
