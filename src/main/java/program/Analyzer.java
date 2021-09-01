package program;

import core.VirtualProject;
import parser.ProjectParser;
import parser.validator.ParserValidator;

import java.util.Optional;

public class Analyzer {
    public static void main(String[] args) {
        ParserValidator parserValidator = ParserValidator.create();
        ProjectParser projectParser = ProjectParser.create(parserValidator);
        Optional<VirtualProject> project = projectParser.parseProject("/home/nika/personal_stuff/personal_projects/project-analyzer");
        if (project.isPresent()) {
            System.out.println(project);
        } else {
            System.out.println("No project to print.\n");
        }
    }
}
