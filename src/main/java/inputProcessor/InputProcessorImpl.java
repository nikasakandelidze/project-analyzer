package inputProcessor;

import inputProcessor.messages.InputMessage;
import inputProcessor.messages.InputMessageType;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

class InputProcessorImpl implements InputProcessor {
    private final Scanner scanner = new Scanner(new InputStreamReader(System.in));

    @Override
    public InputMessage getInput() {
        System.out.print("> ");
        String line = scanner.nextLine();
        if (line.contains("ls -xc")) {
            if (line.length() == 6) {
                return new InputMessage(InputMessageType.COUNT_ALL_EXTENSIONS, List.of());
            } else {
                String extension = line.substring(7);
                return new InputMessage(InputMessageType.COUNT_WITH_EXTENSION, List.of(extension));
            }
        } else if (line.contains("ls -x ")) {
            String extension = line.substring(6);
            return new InputMessage(InputMessageType.LS_WITH_EXTENSION, List.of(extension));
        } else if (line.contains("help")) {
            String helpDirective = line.trim().substring(4);
            return new InputMessage(InputMessageType.HELP, List.of(helpDirective));
        } else if (line.contains("exit")) {
            return new InputMessage(InputMessageType.EXIT, List.of());
        } else if (line.contains("commit count")) {
            return new InputMessage(InputMessageType.GIT_USERS_COMMITS_COUNTS, List.of());
        } else if (line.contains("commit edge")) {
            return new InputMessage(InputMessageType.GIT_USERS_COMMITS_EDGES, List.of());
        } else if (line.contains("commit date")) {
            return new InputMessage(InputMessageType.GIT_USERS_COMMITS_DATE, List.of());
        } else if(line.contains("tree")){
            return new InputMessage(InputMessageType.TREE, List.of());
        }
        else {
            return null;
        }
    }
}
