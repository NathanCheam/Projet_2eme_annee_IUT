package main.process.verification;

/**
 * Abstract decorator class for validators.
 * This class implements the IValidator interface and delegates the validation to the wrapped validator.
 * @author Notteau Romain
 * @author Fauquet Sacha
 * @author Derancourt Louis
 */
public abstract class DecoValidator implements IValidator {
    private final IValidator validator;

    /**
     * Constructor for DecoValidator.
     *
     * @param validator The validator to be decorated.
     */
    DecoValidator(IValidator validator){
        this.validator = validator;
    }

    /**
     * Validates the given strings by delegating to the wrapped validator.
     *
     * @param out The output string to be validated.
     * @param ans The answer string to be compared against.
     * @return true if the validation is successful, false otherwise.
     */
    public boolean valid(String out, String ans){
        return validator.valid(out, ans);
    }
}