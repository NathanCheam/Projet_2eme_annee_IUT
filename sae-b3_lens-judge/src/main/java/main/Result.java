package main;

/**
 * Result of the Judge
 * This class represents the result of a judging process, including its validity and an associated message.
 *
 * @author Louis Derancourt
 */
public class Result {

    /**
     * Validity of the result
     */
    public final boolean valid;

    /**
     * Message of the result
     */
    public final String message;

    /**
     * Constructor
     *
     * @param valid   Indicates whether the result is valid
     * @param message The message associated with the result
     */
    public Result(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    /**
     * Merges two Result objects into one.
     * The merged result is valid only if both input results are valid.
     * The message of the merged result is a combination of the messages from both input results.
     *
     * @param result1 The first result to merge
     * @param result2 The second result to merge
     * @return A new Result object representing the merged result
     */
    public static Result merge(Result result1, Result result2) {
        return new Result((result1.valid && result2.valid), result2.message + System.lineSeparator() + result1.message);
    }

    /**
     * Returns a string representation of the result.
     * The string includes the validity status and the message.
     *
     * @return A string representation of the result
     */
    public String toString() {
        return "[" + (valid ? "Valid" : "Invalid") + "]" + " :\n" + message;
    }

}