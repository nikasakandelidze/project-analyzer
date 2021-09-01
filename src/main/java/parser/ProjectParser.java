package parser;

import core.VirtualProject;
import parser.validator.ParserValidator;

import java.util.Optional;

public interface ProjectParser {

    Optional<VirtualProject> parseProject(String absolutePath);

    static ProjectParser create(ParserValidator parserValidator) {
        return new ProjectParserImpl(parserValidator);
    }
}
