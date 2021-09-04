package service.gitProcessor;

public interface GitProcessor {
    GitDataMessage processGitData(String path);

    static GitProcessor create() {
        return new GitProcessorImpl();
    }
}
