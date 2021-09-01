package core;

public class VirtualFileMetadata {
    private long size;
    private String extension;

    public double getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "VirtualFileMetadata{" +
                "size=" + size +
                ", extension='" + extension + '\'' +
                '}';
    }
}
