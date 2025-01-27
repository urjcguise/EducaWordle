package app.TFGWordle.dto;

import app.TFGWordle.model.Folder;

public class FolderDTO {

    private Long id;
    private String name;
    private FolderDTO parentFolder;

    public FolderDTO(Folder folder) {
        this.id = folder.getId();
        this.name = folder.getName();
        this.parentFolder = folder.getParentFolder() != null ? new FolderDTO(folder.getParentFolder()) : null;
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

    public FolderDTO getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(FolderDTO parentFolder) {
        this.parentFolder = parentFolder;
    }
}
