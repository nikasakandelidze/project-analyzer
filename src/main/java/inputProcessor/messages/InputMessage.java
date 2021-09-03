package inputProcessor.messages;

import java.util.List;

public class InputMessage {
    private InputMessageType inputMessageType;
    private List<String> arguments;

    public InputMessage(InputMessageType inputMessageType, List<String> arguments) {
        this.inputMessageType = inputMessageType;
        this.arguments = arguments;
    }

    public InputMessageType getInputMessageType() {
        return inputMessageType;
    }

    public void setInputMessageType(InputMessageType inputMessageType) {
        this.inputMessageType = inputMessageType;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }
}
