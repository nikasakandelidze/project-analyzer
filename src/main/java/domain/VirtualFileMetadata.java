package domain;

public class VirtualFileMetadata {
    private long size;
    private String extension;
    private long lastModified;

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

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "VirtualFileMetadata{" +
                "size=" + size +
                ", extension='" + extension + '\'' +
                '}';
    }
}
