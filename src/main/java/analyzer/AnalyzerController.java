package analyzer;

import common.Tuple;
import domain.VirtualProject;
import inputProcessor.InputProcessor;
import inputProcessor.messages.InputMessage;
import inputProcessor.messages.InputMessageType;
import presenter.Presenter;
import projectParser.ProjectParser;
import projectParser.validator.ProjectParserValidator;
import service.VirtualProjectProcessor;
import service.output.ProcessorMessage;
import service.statisticalProcessor.metadata.model.FileExtensionsStatisticsModel;

import java.util.List;
import java.util.Map;
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
        FileExtensionsStatisticsModel fileExtensionsStatisticsModel = processorMessage.getExtensionModel().get();
        while (true) {
            InputMessage input = inputProcessor.getInput();
            if (input != null) {
                InputMessageType inputMessageType = input.getInputMessageType();
                if (inputMessageType == InputMessageType.COUNT_WITH_EXTENSION) {
                    String extension = input.getArguments().get(0);
                    long numberOfFilesWithExtension = fileExtensionsStatisticsModel.getNumberOfFilesWithExtension(extension);
                    presenter.showMessage(String.format("Number of files with extension: %s is: %d%n", extension, numberOfFilesWithExtension));
                } else if (inputMessageType == InputMessageType.LS_WITH_EXTENSION) {
                    String extension = input.getArguments().get(0);
                    List<String> extensions = fileExtensionsStatisticsModel.getNamesOfFilesWithExtension(extension);
                    extensions.forEach(presenter::showMessage);
                } else if (inputMessageType == InputMessageType.COUNT_ALL_EXTENSIONS) {
                    Map<String, Integer> mapWithExtensionCounts = fileExtensionsStatisticsModel.getMapWithExtensionCounts();
                    presenter.showMessage(" <File>  : <Count>");
                    mapWithExtensionCounts.keySet().forEach(e -> {
                        presenter.showMessage(String.format("%s : %d", e, mapWithExtensionCounts.get(e)));
                    });
                    Optional<Tuple<String, Integer>> max = mapWithExtensionCounts.keySet().stream()
                            .map(e -> new Tuple<>(e, mapWithExtensionCounts.get(e)))
                            .max((a, b) -> a.getSecond() - b.getSecond());
                    Tuple<String, Integer> stringIntegerTuple = max.get();
                    presenter.showMessage("Extension with most files: " + stringIntegerTuple.getFirst() + ", Count: " + stringIntegerTuple.getSecond());
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
