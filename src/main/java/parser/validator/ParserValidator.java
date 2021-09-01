package parser.validator;

public interface ParserValidator {
    ValidationResult validate(String absoluePath);

    static ParserValidator create(){
        return new ParserValidatorImpl();
    }
}
