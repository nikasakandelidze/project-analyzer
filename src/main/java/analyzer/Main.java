package analyzer;

import inputProcessor.InputProcessor;
import presenter.Presenter;
import service.VirtualProjectProcessor;

public class Main {
    public static void main(String[] args) {
        String pathToPRoject = ".";
        if (args.length == 1) {
            pathToPRoject = args[0];
        }
        Presenter presenter = Presenter.create();
        VirtualProjectProcessor processor = VirtualProjectProcessor.create();
        InputProcessor inputProcessor = InputProcessor.create();
        AnalyzerController analyzerController = new AnalyzerController(presenter, processor, inputProcessor);
        analyzerController.analyze(pathToPRoject);
    }
}
