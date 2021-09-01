package projectParser;

import core.VirtualDir;
import core.VirtualFile;
import core.VirtualFileMetadata;
import core.VirtualProject;
import org.apache.commons.io.FilenameUtils;
import projectParser.validator.ProjectParserValidator;
import projectParser.validator.ValidationResult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static projectParser.utils.ProjectParserUtils.getNameForFolder;

class ProjectParserImpl implements ProjectParser {
    private final ProjectParserValidator parserValidator;
    public static final String BLANK = "BLANK";

    public ProjectParserImpl(ProjectParserValidator projectParserValidator) {
        this.parserValidator = projectParserValidator;
    }

    @Override
    public Optional<VirtualProject> parseProject(String absolutePath) {
        ValidationResult validate = parserValidator.validate(absolutePath);
        if (validate.isValid()) {
            VirtualProject project = new VirtualProject();
            VirtualDir rootDir = new VirtualDir();
            rootDir.setName(getNameForFolder(absolutePath).orElse("rootFolder"));
            project.setRoot(rootDir);
            walk(absolutePath, rootDir);
            return Optional.of(project);
        } else {
            return Optional.empty();
        }
    }

    void walk(String path, VirtualDir parentDir) {
        File root = new File(path);
        File[] list = root.listFiles();
        if (list == null) return;
        for (File f : list) {
            if (f.isDirectory()) {
                VirtualDir subDir = new VirtualDir();
                subDir.setName(f.getName());
                walk(f.getAbsolutePath(), subDir);
                parentDir.addDir(subDir);
            } else {
                VirtualFile file = new VirtualFile();
                file.setName(f.getName());
                tryToSetContent(f, file);
                VirtualFileMetadata metadata = new VirtualFileMetadata();
                metadata.setExtension(FilenameUtils.getExtension(f.getName()));
                metadata.setSize(f.length());
                metadata.setLastModified(f.lastModified());
                file.setMetadata(metadata);
                parentDir.addFile(file);
            }
        }
    }

    private void tryToSetContent(File f, VirtualFile file) {
        try {
            file.setContent(Files.readString(Path.of(f.getAbsolutePath()), StandardCharsets.US_ASCII));
        } catch (IOException ignored) {
            file.setContent(BLANK);
            //add logging or exceptional handling
        }
    }
}
