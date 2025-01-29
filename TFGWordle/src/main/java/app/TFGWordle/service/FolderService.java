package app.TFGWordle.service;

import app.TFGWordle.model.Folder;
import app.TFGWordle.repository.FolderRepository;
import app.TFGWordle.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService {

    @Autowired
    private FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public void save(Folder folder) {
        folderRepository.save(folder);
    }

    public List<Folder> getByProfessor(User professor) {
        return folderRepository.findByProfessor(professor);
    }

    public boolean existsByName(String folderName) {
        return folderRepository.findByName(folderName);
    }

    public void delete(Folder folder) {
        folderRepository.delete(folder);
    }

    public boolean existsById(Long id) {
        return folderRepository.existsById(id);
    }

    public Folder getById(Long id) {
        return folderRepository.getById(id);
    }
}
