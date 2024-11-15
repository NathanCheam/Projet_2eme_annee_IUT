package main.process.verification;

/**
 * Interface for validators.
 * This interface defines a method for validating two strings.
 * @author Fauquet Sacha
 */
public interface IValidator {

    /**
     * Validates the given strings.
     *
     * @param out The output string to be validated.
     * @param ans The answer string to be compared against.
     * @return true if the validation is successful, false otherwise.
     */
    boolean valid(String out, String ans);
}