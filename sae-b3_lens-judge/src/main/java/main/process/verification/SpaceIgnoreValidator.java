package main.process.verification;

/**
 * Validator that ignores spaces in the strings being compared.
 * This class extends DecoValidator and overrides the valid method to ignore spaces.
 * @author Notteau Romain
 * @author Fauquet Sacha
 */
public class SpaceIgnoreValidator extends DecoValidator {

    /**
     * Constructor for SpaceIgnoreValidator.
     *
     * @param validator The validator to be decorated.
     */
    public SpaceIgnoreValidator(IValidator validator) {
        super(validator);
    }

    /**
     * Validates the given strings by ignoring spaces.
     *
     * @param out The output string to be validated.
     * @param ans The answer string to be compared against.
     * @return true if the strings are equal when spaces are ignored, false otherwise.
     */
    @Override
    public boolean valid(String out, String ans) {
        String newOut = out.replaceAll("\\s", "");
        String newAns = ans.replaceAll("\\s", "");
        return super.valid(newOut, newAns);
    }
}