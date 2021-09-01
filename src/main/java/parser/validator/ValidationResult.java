package parser.validator;

import java.util.Set;

public class ValidationResult {
    private final Set<String> messages;
    private final boolean isValid;

    public ValidationResult(Set<String> messages, boolean isValid) {
        this.messages = messages;
        this.isValid = isValid;
    }

    public Set<String> getMessages() {
        return messages;
    }

    public boolean isValid() {
        return isValid;
    }

    public static ValidationResult valid(){
        return new ValidationResult(Set.of(), true);
    }

    public static ValidationResult invalid(Set<String> messages){
        return new ValidationResult(messages, false);
    }
}
