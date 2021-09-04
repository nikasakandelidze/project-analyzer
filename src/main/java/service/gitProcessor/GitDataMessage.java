package service.gitProcessor;

import service.gitProcessor.entity.Commit;

import java.util.List;

public class GitDataMessage {
    private final List<Commit> commits;

    public GitDataMessage(List<Commit> commits) {
        this.commits = commits;
    }

    public List<Commit> getCommits() {
        return commits;
    }
}
