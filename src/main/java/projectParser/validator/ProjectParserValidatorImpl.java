package projectParser.validator;

import java.io.File;
import java.util.Set;

class ProjectParserValidatorImpl implements ProjectParserValidator {
    @Override
    public ValidationResult validate(String absoluePath) {
        File root = new File(absoluePath);
        if (!root.isDirectory()) {
            return ValidationResult.invalid(Set.of("Path must lead to a folder."));
        } else {
            return ValidationResult.valid();
        }
    }
}
