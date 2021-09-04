package service.gitProcessor.entity;

import java.util.Date;
import java.util.List;

public class Commit {
    private final String message;
    private final List<String> filesNames;
    private final Date date;
    private final User author;

    public Commit(String message, List<String> filesNames, Date date, User author) {
        this.message = message;
        this.filesNames = filesNames;
        this.date = date;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getFilesNames() {
        return filesNames;
    }

    public Date getDate() {
        return date;
    }

    public User getAuthor() {
        return author;
    }
}
