package app.TFGWordle.dto;

import app.TFGWordle.model.Folder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FolderDTO {

    private Long id;
    private String name;
    private List<WordleDTO> wordles;
    private List<FolderDTO> folders;
    private FolderDTO parentFolder;

    public FolderDTO(Folder folder, boolean includeChildren) {
        this.id = folder.getId();
        this.name = folder.getName();
        this.wordles = folder.getWordles().stream()
                .map(WordleDTO::new)
                .collect(Collectors.toList());
        this.parentFolder = folder.getParentFolder() != null ? new FolderDTO(folder.getParentFolder(), false) : null;

        if (includeChildren) {
            this.folders = folder.getFolders().stream()
                    .map(child -> new FolderDTO(child, false))
                    .collect(Collectors.toList());
        } else {
            this.folders = new ArrayList<>();
        }
    }

    public FolderDTO(Folder folder) {
        this(folder, true);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WordleDTO> getWordles() {
        return wordles;
    }

    public void setWordles(List<WordleDTO> wordles) {
        this.wordles = wordles;
    }

    public List<FolderDTO> getFolders() {
        return folders;
    }

    public void setFolders(List<FolderDTO> folders) {
        this.folders = folders;
    }

    public FolderDTO getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(FolderDTO parentFolder) {
        this.parentFolder = parentFolder;
    }
}
