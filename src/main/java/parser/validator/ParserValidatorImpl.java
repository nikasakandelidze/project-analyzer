package parser.validator;

import java.io.File;
import java.util.Set;

class ParserValidatorImpl implements ParserValidator {
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
