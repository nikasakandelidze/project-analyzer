package service.gitProcessor;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.lib.Repository;
import service.gitProcessor.entity.Commit;
import service.gitProcessor.entity.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class GitProcessorImpl implements GitProcessor {

    @Override
    public GitDataMessage processGitData(String path) {
        try {
            Git git = Git.open(new File(path));
            LogCommand log = git.log();
            List<Commit> commits = StreamSupport.stream(log.call()
                            .spliterator(), false)
                    .map(e -> {
                        User user = new User(e.getAuthorIdent().getName(), e.getAuthorIdent().getEmailAddress());
                        return new Commit(e.getFullMessage(), List.of(), e.getAuthorIdent().getWhen(), user);
                    }).collect(Collectors.toList());
            return new GitDataMessage(commits);
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            return new GitDataMessage(List.of());
        }
    }
}
