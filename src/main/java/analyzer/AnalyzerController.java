package analyzer;

import core.VirtualProject;
import presenter.Presenter;
import service.VirtualProjectProcessor;
import service.output.ProcessorMessage;
import service.statisticalProcessor.metadata.model.ExtensionsStatisticsModel;
import projectParser.ProjectParser;
import projectParser.validator.ProjectParserValidator;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AnalyzerController {
    private final Presenter presenter;
    private final VirtualProjectProcessor processor;


    public AnalyzerController(Presenter presenter, VirtualProjectProcessor processor) {
        this.presenter = presenter;
        this.processor = processor;
    }

    public void analyze(String path) {
        Optional<VirtualProject> project = initProjectAnalyzer(path);
        ProcessorMessage processorMessage = processor.processVirtualProject(project.get());
        ExtensionsStatisticsModel extensionsStatisticsModel = processorMessage.getExtensionModel().get();
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        while (true) {
            presenter.showMessage("> ");
            String inputLine = scanner.nextLine();
            if (inputLine.contains("find -x ")) {
                String extension = inputLine.substring(8);
                long numberOfFilesWithExtension = extensionsStatisticsModel.getNumberOfFilesWithExtension(extension);
                presenter.showMessage(String.format("Number of files with extension: %s is: %d%n", extension, numberOfFilesWithExtension));
                presenter.showMessage("Input ls to list all files");
                presenter.showMessage(">");
                String nextLine = scanner.nextLine();
                if (nextLine.equals("ls")) {
                    List<String> extensions = extensionsStatisticsModel.getNamesOfFilesWithExtension(extension);
                    extensions.forEach(presenter::showMessage);
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
