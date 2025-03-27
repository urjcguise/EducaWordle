package app.TFGWordle.service;

import app.TFGWordle.model.Folder;
import app.TFGWordle.repository.FolderRepository;
import app.TFGWordle.security.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FolderServiceTest {

    @Mock
    private FolderRepository folderRepository;

    @InjectMocks
    private FolderService folderService;

    @Test
    void save() {
        Folder folder = new Folder();
        folderService.save(folder);
        verify(folderRepository, times(1)).save(folder);
    }

    @Test
    void getByProfessor() {
        Long id = 1L;

        User professor = new User();
        Folder folder1 = new Folder();
        folder1.setId(id);
        folder1.setProfessor(professor);
        Folder folder2 = new Folder();
        folder2.setId(id);
        folder2.setProfessor(professor);
        List<Folder> folders = List.of(folder1, folder2);

        when(folderRepository.findByProfessor(professor)).thenReturn(folders);

        List<Folder> result = folderService.getByProfessor(professor);

        assertNotNull(result);
        assertEquals(folders.size(), result.size());
        assertEquals(folder1.getId(), result.get(0).getId());
        assertEquals(folder2.getId(), result.get(1).getId());
    }

    @Test
    void getByProfessorEmptyList() {
        Long id = 1L;

        User professor = new User();

        when(folderRepository.findByProfessor(professor)).thenReturn(new ArrayList<>());

        List<Folder> result = folderService.getByProfessor(professor);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void existsByNameExists() {
        String folderName = "Folder";
        when(folderRepository.findByName(folderName)).thenReturn(true);
        assertTrue(folderService.existsByName(folderName));
    }

    @Test
    void existsByNameNotExists() {
        String folderName = "Folder";
        when(folderRepository.findByName(folderName)).thenReturn(false);
        assertFalse(folderService.existsByName(folderName));
    }

    @Test
    void delete() {
        Folder folder = new Folder();
        folderService.delete(folder);
        verify(folderRepository, times(1)).delete(folder);
    }

    @Test
    void existsByIdExists() {
        Long id = 1L;
        when(folderRepository.existsById(id)).thenReturn(true);
        assertTrue(folderService.existsById(id));
    }

    @Test
    void existsByIdNotExists() {
        Long id = 1L;
        when(folderRepository.existsById(id)).thenReturn(false);
        assertFalse(folderService.existsById(id));
    }

    @Test
    void getById() {
        Long id = 1L;
        Folder folder = new Folder();
        folder.setId(id);

        when(folderRepository.getById(id)).thenReturn(folder);

        Folder result = folderService.getById(id);

        assertNotNull(result);
        assertEquals(folder.getId(), result.getId());
    }
}