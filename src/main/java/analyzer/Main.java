package analyzer;

import inputProcessor.InputProcessor;
import presenter.Presenter;
import service.VirtualProjectProcessor;

public class Main {
    public static void main(String[] args) {
        Presenter presenter = Presenter.create();
        VirtualProjectProcessor processor = VirtualProjectProcessor.create();
        InputProcessor inputProcessor = InputProcessor.create();
        AnalyzerController analyzerController = new AnalyzerController(presenter, processor, inputProcessor);
        analyzerController.analyze("/home/nika/personal_stuff/personal_projects/project-analyzer");
    }
}
