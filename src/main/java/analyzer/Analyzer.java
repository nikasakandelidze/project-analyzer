package analyzer;

import core.VirtualProject;
import processor.VirtualProjectProcessor;
import processor.output.ProcessorMessage;
import processor.statisticalProcessor.metadata.model.ExtensionsStatisticsModel;
import projectParser.ProjectParser;
import projectParser.validator.ProjectParserValidator;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Analyzer {

    public static void main(String[] args) {
        Optional<VirtualProject> project = initProjectAnalyzer("/home/nika/personal_stuff/personal_projects/project-analyzer");
        VirtualProjectProcessor processor = VirtualProjectProcessor.create();
        ProcessorMessage processorMessage = processor.processVirtualProject(project.get());
        ExtensionsStatisticsModel extensionsStatisticsModel = processorMessage.getExtensionModel().get();
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        while (true) {
            System.out.print("> ");
            String inputLine = scanner.nextLine();
            if (inputLine.contains("find -x ")) {
                String extension = inputLine.substring(8);
                long numberOfFilesWithExtension = extensionsStatisticsModel.getNumberOfFilesWithExtension(extension);
                System.out.printf("Number of files with extension: %s is: %d%n", extension, numberOfFilesWithExtension);
                System.out.println("Input ls to list all files");
                String nextLine = scanner.nextLine();
                if (nextLine.equals("ls")) {
                    List<String> extensions = extensionsStatisticsModel.getNamesOfFilesWithExtension(extension);
                    extensions.forEach(System.out::println);
                } else {

                }
            }
        }
    }

    private static Optional<VirtualProject> initProjectAnalyzer(String projectPath) {
        ProjectParserValidator parserValidator = ProjectParserValidator.create();
        ProjectParser projectParser = ProjectParser.create(parserValidator);
        return projectParser.parseProject(projectPath);
    }

}
