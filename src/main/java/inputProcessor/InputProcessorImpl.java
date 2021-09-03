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
        System.out.print(">");
        String line = scanner.nextLine();
        if (line.contains("find -xc")) {
            if (line.length() == 8) {
                return new InputMessage(InputMessageType.COUNT_ALL_EXTENSIONS, List.of());
            } else {
                String extension = line.substring(9);
                return new InputMessage(InputMessageType.COUNT_WITH_EXTENSION, List.of(extension));
            }
        } else if (line.contains("find -xl ")) {
            String extension = line.substring(9);
            return new InputMessage(InputMessageType.LS_WITH_EXTENSION, List.of(extension));
        } else if (line.contains("help")) {
            String helpDirective = line.trim().substring(4);
            return new InputMessage(InputMessageType.HELP, List.of(helpDirective));
        } else if (line.contains("exit")) {
            return new InputMessage(InputMessageType.EXIT, List.of());
        } else {
            return null;
        }
    }
}
