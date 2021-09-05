package analyzer;

import common.CollectionsWrapper;
import common.Tuple;
import domain.VirtualProject;
import inputProcessor.InputProcessor;
import inputProcessor.messages.InputMessage;
import inputProcessor.messages.InputMessageType;
import presenter.Presenter;
import projectParser.ProjectParser;
import projectParser.validator.ProjectParserValidator;
import service.VirtualProjectProcessor;
import service.gitProcessor.GitDataMessage;
import service.gitProcessor.entity.Commit;
import service.gitProcessor.entity.User;
import service.help.HelpMessage;
import service.output.ProcessorMessage;
import service.statisticalProcessor.metadata.model.FileExtensionsStatisticsModel;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

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
                            .max(Comparator.comparingInt(Tuple::getSecond));
                    Tuple<String, Integer> stringIntegerTuple = max.get();
                    presenter.showMessage("Extension with most files: " + stringIntegerTuple.getFirst() + ", Count: " + stringIntegerTuple.getSecond());
                } else if (inputMessageType == InputMessageType.HELP) {
                    HelpMessage helpMessage = processor.buildHelpMessage();
                    presenter.showMessage(helpMessage.getMessage());
                } else if (inputMessageType == InputMessageType.GIT_USERS_COMMITS_COUNTS) {
                    GitDataMessage gitDataMessage = processor.processGitData(path);
                    Map<User, List<Commit>> commits = getUserListMap(gitDataMessage);
                    presenter.showMessage("     Presenting Users commit statistics:");
                    commits.keySet().forEach(user -> {
                        presenter.showMessage(String.format("User %s (email: %s).\n - Number of commits: %d.", user.getName(), user.getEmail(), commits.get(user).size()));
                    });
                } else if (inputMessageType == InputMessageType.GIT_USERS_COMMITS_EDGES) {
                    GitDataMessage gitDataMessage = processor.processGitData(path);
                    Map<User, List<Commit>> commits = getUserListMap(gitDataMessage);
                    Optional<Map.Entry<User, List<Commit>>> max = commits.entrySet().stream().max((a, b) -> a.getValue().size() - b.getValue().size());
                    Optional<Map.Entry<User, List<Commit>>> min = commits.entrySet().stream().min((a, b) -> a.getValue().size() - b.getValue().size());
                    if (max.isPresent()) {
                        Map.Entry<User, List<Commit>> maxData = max.get();
                        Map.Entry<User, List<Commit>> minData = min.get();
                        presenter.showMessage(String.format("User %s ( email: %s ) has max number of commits. Number of commits: %d", maxData.getKey().getName(), maxData.getKey().getEmail(), maxData.getValue().size()));
                        presenter.showMessage(String.format("User %s ( email: %s ) has min number of commits. Number of commits: %d", minData.getKey().getName(), minData.getKey().getEmail(), minData.getValue().size()));
                    } else {
                        presenter.showMessage("Can't find edge data for commits.");
                    }
                } else if (inputMessageType == InputMessageType.GIT_USERS_COMMITS_DATE) {
                    GitDataMessage gitDataMessage = processor.processGitData(path);
                    Map<String, List<Commit>> dateListMap = getDateListMap(gitDataMessage);
                    dateListMap.keySet().forEach(date -> {
                        List<Commit> commits = dateListMap.get(date);
                        Tuple<User, Long> mostActiveUserOfDate = getMostActiveUserOfDate(date, dateListMap);
                        Tuple<User, Long> leastActiveUserOfDate = getLeastActiveUserOfDate(date, dateListMap);
                        presenter.showMessage("Date: " + date.toString() + ". Total Number of commits: " + commits.size());
                        presenter.showMessage("  (MAX) - User:" + mostActiveUserOfDate.getFirst() + " had most commits of: " + mostActiveUserOfDate.getSecond() + " on this day.");
                        presenter.showMessage("  (MIN) - User:" + leastActiveUserOfDate.getFirst() + " had least commits of: " + leastActiveUserOfDate.getSecond() + " on this day.");
                    });
                } else if (inputMessageType == InputMessageType.EXIT) {
                    presenter.showMessage("Exiting file analyzer.");
                    break;
                }
            }
        }
    }

    private Tuple<User, Long> getMostActiveUserOfDate(String date, Map<String, List<Commit>> dateListMap) {
        List<Commit> commits = dateListMap.get(date);
        Map<User, Long> collect = commits.stream()
                .filter(e -> getDateString(e.getDate()).equals(date))
                .collect(Collectors.groupingBy(Commit::getAuthor, counting()));
        Optional<Map.Entry<User, Long>> max = collect.entrySet().stream().max((a, b) -> (int) (a.getValue() - b.getValue()));
        return max.map(e -> new Tuple<User, Long>(e.getKey(), e.getValue())).orElse(null);
    }

    private Tuple<User, Long> getLeastActiveUserOfDate(String date, Map<String, List<Commit>> dateListMap) {
        List<Commit> commits = dateListMap.get(date);
        Map<User, Long> collect = commits.stream()
                .filter(e -> getDateString(e.getDate()).equals(date))
                .collect(Collectors.groupingBy(Commit::getAuthor, counting()));
        Optional<Map.Entry<User, Long>> max = collect.entrySet().stream().min((a, b) -> (int) (a.getValue() - b.getValue()));
        return max.map(e -> new Tuple<User, Long>(e.getKey(), e.getValue())).orElse(null);
    }

    private Map<User, List<Commit>> getUserListMap(GitDataMessage gitDataMessage) {
        Map<User, List<Commit>> commits = new HashMap<>();
        gitDataMessage.getCommits()
                .forEach(commit -> {
                    User author = commit.getAuthor();
                    if (commits.containsKey(author)) {
                        commits.get(author).add(commit);
                    } else {
                        commits.put(author, CollectionsWrapper.mutableListWithFirstElement(commit));
                    }
                });
        return commits;
    }

    private Map<String, List<Commit>> getDateListMap(GitDataMessage gitDataMessage) {
        Map<String, List<Commit>> commits = new HashMap<>();
        gitDataMessage.getCommits()
                .forEach(commit -> {
                    Date date = commit.getDate();
                    String currentDateKey = getDateString(date);
                    if (commits.containsKey(currentDateKey)) {
                        commits.get(currentDateKey).add(commit);
                    } else {
                        commits.put(currentDateKey, CollectionsWrapper.mutableListWithFirstElement(commit));
                    }
                });
        return commits;
    }

    private String getDateString(Date date) {
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        String day = dayFormat.format(date);
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
        String month = monthFormat.format(date);
        return day + " " + month;
    }

    private static Optional<VirtualProject> initProjectAnalyzer(String projectPath) {
        ProjectParserValidator parserValidator = ProjectParserValidator.create();
        ProjectParser projectParser = ProjectParser.create(parserValidator);
        return projectParser.parseProject(projectPath);
    }
}
