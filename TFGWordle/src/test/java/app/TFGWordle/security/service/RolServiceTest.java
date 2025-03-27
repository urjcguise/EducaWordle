package app.TFGWordle.security.service;

import app.TFGWordle.security.entity.Rol;
import app.TFGWordle.security.enums.RolName;
import app.TFGWordle.security.repository.RolRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RolServiceTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolService rolService;

    @Test
    void save() {
        Rol rol = new Rol();
        rolService.saveRol(rol);
        verify(rolRepository, times(1)).save(rol);
    }

    @Test
    void getRolFounded() {
        RolName rolName = RolName.ROLE_PROFESSOR;
        Rol rol = new Rol();
        rol.setRolName(rolName);

        when(rolRepository.findByRolName(rolName)).thenReturn(Optional.of(rol));

        Optional<Rol> rolReturned = rolService.getRol(rolName);

        verify(rolRepository, times(1)).findByRolName(rolName);
        assertTrue(rolReturned.isPresent());
        assertEquals(rol.getRolName(), rolReturned.get().getRolName());
    }

    @Test
    void getRolNotFounded() {
        RolName rolName = RolName.ROLE_PROFESSOR;
        when(rolRepository.findByRolName(rolName)).thenReturn(Optional.empty());

        Optional<Rol> rolReturned = rolService.getRol(rolName);

        verify(rolRepository, times(1)).findByRolName(rolName);
        assertTrue(rolReturned.isEmpty());
    }
}