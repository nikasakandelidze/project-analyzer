package analyzer;

import core.VirtualProject;
import processor.VirtualProjectProcessor;
import processor.output.ProcessorMessage;
import processor.statisticalProcessor.metadata.model.ExtensionsStatisticsModel;
import projectParser.ProjectParser;
import projectParser.validator.ProjectParserValidator;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Analyzer {
    public static void main(String[] args) {
        ProjectParserValidator parserValidator = ProjectParserValidator.create();
        ProjectParser projectParser = ProjectParser.create(parserValidator);

        VirtualProjectProcessor processor = VirtualProjectProcessor.create();

        Optional<VirtualProject> project = projectParser.parseProject("/home/nika/personal_stuff/personal_projects/project-analyzer");
        if (project.isPresent()) {
            ProcessorMessage processorMessage = processor.processVirtualProject(project.get());
            ExtensionsStatisticsModel extensionsStatisticsModel = processorMessage.getExtensionModel().get();
            Set<String> extensions = extensionsStatisticsModel.getExtensions();
            extensions.forEach(e -> {
                List<String> namesOfFilesWithExtension = extensionsStatisticsModel.getNamesOfFilesWithExtension(e);
                System.out.println(e + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                namesOfFilesWithExtension.forEach(r -> System.out.println(r + " "));
            });
        } else {
            System.out.println("No project to print.\n");
        }
    }
}
