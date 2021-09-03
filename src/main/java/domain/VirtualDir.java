package domain;

import java.util.ArrayList;
import java.util.List;

public class VirtualDir {
    private String name;
    private List<VirtualFile> files = new ArrayList<>();
    private List<VirtualDir> directories = new ArrayList<>();

    public List<VirtualFile> getFiles() {
        return files;
    }

    public void setFiles(List<VirtualFile> files) {
        this.files = files;
    }

    public List<VirtualDir> getDirectories() {
        return directories;
    }

    public void setDirectories(List<VirtualDir> directories) {
        this.directories = directories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDir(VirtualDir dir) {
        this.directories.add(dir);
    }

    public void addFile(VirtualFile file) {
        this.files.add(file);
    }

    @Override
    public String toString() {
        return "VirtualDir{" +
                "name='" + name + '\'' +
                ", files=" + files +
                ", directories=" + directories +
                '}';
    }
}
