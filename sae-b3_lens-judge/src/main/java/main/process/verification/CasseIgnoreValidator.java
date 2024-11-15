package main.process.verification;

/**
 * Validator that ignores case in the strings being compared.
 * This class extends DecoValidator and overrides the valid method to ignore case.
 * @author Notteau Romain
 * @author Fauquet Sacha
 */
public class CasseIgnoreValidator extends DecoValidator {

    /**
     * Constructor for CasseIgnoreValidator.
     *
     * @param validator The validator to be decorated.
     */
    public CasseIgnoreValidator(IValidator validator) {
        super(validator);
    }

    /**
     * Validates the given strings by ignoring case.
     *
     * @param out The output string to be validated.
     * @param ans The answer string to be compared against.
     * @return true if the strings are equal when case is ignored, false otherwise.
     */
    @Override
    public boolean valid(String out, String ans) {
        String newOut = out.toLowerCase();
        String newAns = ans.toLowerCase();
        return super.valid(newOut, newAns);
    }
}