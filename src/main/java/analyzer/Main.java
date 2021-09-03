package analyzer;

import presenter.Presenter;
import service.VirtualProjectProcessor;

public class Main {
    public static void main(String[] args) {
        AnalyzerController analyzerController = new AnalyzerController(Presenter.create(), VirtualProjectProcessor.create());
        analyzerController.analyze("/home/nika/personal_stuff/personal_projects/project-analyzer");
    }
}
