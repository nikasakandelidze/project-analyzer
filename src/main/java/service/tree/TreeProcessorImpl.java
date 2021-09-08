package service.tree;

import domain.VirtualDir;
import domain.VirtualFile;
import domain.VirtualFileMetadata;
import domain.VirtualProject;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

class TreeProcessorImpl implements TreeProcessor{
    @Override
    public TreeMessage processTree(VirtualProject virtualProject, Consumer<String> presenter) {
        presenter.accept(virtualProject.getRoot().getName());
        walk(virtualProject.getRoot(), presenter, 1);
        return new TreeMessage();
    }

    void walk(VirtualDir parentDir, Consumer<String> consumer, int levelOfIndent) {
        List<VirtualFile> files = parentDir.getFiles();
        if(files != null && !files.isEmpty()){
            for (VirtualFile file : files) {
                consumer.accept(" ".repeat(levelOfIndent) + "-" + file.getName());
            }
        }
        List<VirtualDir> list = parentDir.getDirectories();
        if (list == null || list.isEmpty()) return;
        for (VirtualDir virtualDir : list) {
            consumer.accept(" ".repeat(levelOfIndent) + "-" + virtualDir.getName() +"(FOLDER)");
            walk(virtualDir, consumer, levelOfIndent+2);
        }
    }
}
