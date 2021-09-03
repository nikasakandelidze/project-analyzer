package domain;

public class VirtualProject {
    private VirtualDir root;

    public VirtualDir getRoot() {
        return root;
    }

    public void setRoot(VirtualDir root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "VirtualProject{" +
                "root=" + root +
                '}';
    }
}
