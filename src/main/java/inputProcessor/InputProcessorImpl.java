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
        if (line.contains("find -xc ")) {
            String extension = line.substring(9);
            return new InputMessage(InputMessageType.COUNT_WITH_EXTENSION, List.of(extension));
        } else if (line.contains("find -xl ")) {
            String extension = line.substring(9);
            return new InputMessage(InputMessageType.LS_WITH_EXTENSION, List.of(extension));
        } else if (line.contains("exit")) {
            return new InputMessage(InputMessageType.EXIT, List.of());
        } else {
            return null;
        }
    }
}
