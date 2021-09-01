package core;

public class VirtualFile {
    private String name;
    private String content;
    private VirtualFileMetadata metadata;

    public String getName() {
        return name;
    }

    public void setName(String fileName) {
        this.name = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public VirtualFileMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(VirtualFileMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "VirtualFile{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}
