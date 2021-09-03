package inputProcessor;

import inputProcessor.messages.InputMessage;

public interface InputProcessor {
    InputMessage getInput();

    static InputProcessor create() {
        return new InputProcessorImpl();
    }
}
