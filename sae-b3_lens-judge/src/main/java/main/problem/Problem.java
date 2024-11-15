package main.problem;

import main.problem.constraint.IContrainte;
import main.process.verification.IValidator;
import main.process.verification.AbsoluteValidator;
import main.process.verification.DecoValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * The Problem class represents a problem with a list of test cases and constraints.
 *
 * @author Fauquet Sacha
 * @author Cheam Nathan
 */
public class Problem {

    private IValidator validator;


    /**
     * The list of test cases.
     */
    private final ArrayList<TestCase> testCases;  // List of test cases
    /**
     * The list of constraints.
     */
    private final ArrayList<IContrainte> contraintes;  // List of constraints

    /**
     * Constructs a new Problem with empty lists for test cases and constraints.
     */
    protected Problem() {
        this.testCases = new ArrayList<>();
        this.contraintes = new ArrayList<>();
        this.validator = new AbsoluteValidator();
    }

    /**
     * Returns the list of constraints.
     *
     * @return the list of constraints
     */
    public List<IContrainte> getContraintes() {
        return contraintes;
    }

    /**
     * Returns the list of test cases.
     *
     * @return the list of test cases
     */
    public List<TestCase> getTestCases() {
        return testCases;
    }

    /**
     * Returns the validator.
     *
     * @return the validator
     */
    public IValidator getValidator() {
        return validator;
    }

    /**
     * Sets a new validator for the problem.
     *
     * @param validator the new validator to set
     */
    protected void setValidator(DecoValidator validator) {
        this.validator = validator;
    }
}