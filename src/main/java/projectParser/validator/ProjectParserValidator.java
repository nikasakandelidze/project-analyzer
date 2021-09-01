package projectParser.validator;

public interface ProjectParserValidator {
    ValidationResult validate(String absoluePath);

    static ProjectParserValidator create(){
        return new ProjectParserValidatorImpl();
    }
}
