package analyzer;

import domain.VirtualProject;
import inputProcessor.InputProcessor;
import inputProcessor.messages.InputMessage;
import inputProcessor.messages.InputMessageType;
import presenter.Presenter;
import projectParser.ProjectParser;
import projectParser.validator.ProjectParserValidator;
import service.VirtualProjectProcessor;
import service.output.ProcessorMessage;
import service.statisticalProcessor.metadata.model.ExtensionsStatisticsModel;

import java.util.List;
import java.util.Optional;

public class AnalyzerController {
    private final Presenter presenter;
    private final VirtualProjectProcessor processor;
    private final InputProcessor inputProcessor;

    public AnalyzerController(Presenter presenter, VirtualProjectProcessor processor, InputProcessor inputProcessor) {
        this.presenter = presenter;
        this.processor = processor;
        this.inputProcessor = inputProcessor;
    }

    public void analyze(String path) {
        Optional<VirtualProject> project = initProjectAnalyzer(path);
        ProcessorMessage processorMessage = processor.processVirtualProject(project.get());
        ExtensionsStatisticsModel extensionsStatisticsModel = processorMessage.getExtensionModel().get();
        while (true) {
            InputMessage input = inputProcessor.getInput();
            if (input != null) {
                InputMessageType inputMessageType = input.getInputMessageType();
                if (inputMessageType == InputMessageType.COUNT_WITH_EXTENSION) {
                    String extension = input.getArguments().get(0);
                    long numberOfFilesWithExtension = extensionsStatisticsModel.getNumberOfFilesWithExtension(extension);
                    presenter.showMessage(String.format("Number of files with extension: %s is: %d%n", extension, numberOfFilesWithExtension));
                } else if (inputMessageType == InputMessageType.LS_WITH_EXTENSION) {
                    String extension = input.getArguments().get(0);
                    List<String> extensions = extensionsStatisticsModel.getNamesOfFilesWithExtension(extension);
                    extensions.forEach(presenter::showMessage);
                } else if (inputMessageType == InputMessageType.EXIT) {
                    presenter.showMessage("Exiting file analyzer.");
                    break;
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
