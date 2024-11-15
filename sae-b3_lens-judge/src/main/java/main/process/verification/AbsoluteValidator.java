package main.process.verification;

/**
 * Validator that performs an absolute comparison of two strings.
 * This class implements the IValidator interface and overrides the valid method to perform a strict equality check.
 * @author Fauquet Sacha
 */
public class AbsoluteValidator implements IValidator {

    /**
     * Validates the given strings by performing an absolute comparison.
     *
     * @param out The output string to be validated.
     * @param ans The answer string to be compared against.
     * @return true if the strings are exactly equal, false otherwise.
     */
    public boolean valid(String out, String ans) {
        return out.equals(ans);
    }
}