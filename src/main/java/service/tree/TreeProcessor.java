package service.tree;

import domain.VirtualProject;

import java.util.function.Consumer;

public interface TreeProcessor {
    TreeMessage processTree(VirtualProject virtualProject, Consumer<String> presenter);

    static TreeProcessor create(){
        return new TreeProcessorImpl();
    }
}
