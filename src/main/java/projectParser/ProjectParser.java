package projectParser;

import domain.VirtualProject;
import projectParser.validator.ProjectParserValidator;

import java.util.Optional;

public interface ProjectParser {

    Optional<VirtualProject> parseProject(String absolutePath);

    static ProjectParser create(ProjectParserValidator parserValidator) {
        return new ProjectParserImpl(parserValidator);
    }
}
