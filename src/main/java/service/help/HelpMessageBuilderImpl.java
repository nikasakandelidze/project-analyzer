package service.help;

import inputProcessor.messages.InputMessageType;

import java.util.Arrays;
import java.util.Optional;

class HelpMessageBuilderImpl implements HelpMessageBuilder {
    public static final String EMPTY = "";

    @Override
    public HelpMessage getHelpMessage() {
        String message = buildMessage();
        return new HelpMessage(message);
    }


    private String buildMessage() {
        Optional<String> message = Arrays.stream(InputMessageType.values())
                .filter(e -> !e.getCommand().isEmpty())
                .map(e -> "Name: " + e.name() + ".\nCommand: " + e.getCommand() + ".\nDetails:  " + e.getDetails())
                .reduce((a, b) -> a + "\n\n" + b);
        return message.orElse(EMPTY);
    }

}
