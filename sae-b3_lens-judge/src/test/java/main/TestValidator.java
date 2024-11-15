package main;

import main.problem.ProblemBuilder;
import main.process.verification.AbsoluteValidator;
import main.process.verification.CasseIgnoreValidator;
import main.process.verification.SpaceIgnoreValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for various validators.
 * This class contains unit tests for the validators used in the application.
 * @author Cheam Nathan
 */
class TestValidator {

    AbsoluteValidator av;

    /**
     * Tests the AbsoluteValidator.
     * Ensures that the valid method returns true when the strings are exactly the same.
     */
    @Test
    void testAbsoluteValidator() {
        av = new AbsoluteValidator();
        assert av.valid("test", "test");
    }

    /**
     * Tests the CasseIgnoreValidator.
     * Ensures that the valid method returns true when the strings are the same, ignoring case.
     */
    @Test
    void testCasseIgnoreValidator() {
        av = new AbsoluteValidator();
        CasseIgnoreValidator civ = new CasseIgnoreValidator(av);
        assert civ.valid("test", "TEST");
    }

    /**
     * Tests the SpaceIgnoreValidator.
     * Ensures that the valid method returns true when the strings are the same, ignoring spaces.
     */
    @Test
    void testSpaceIgnoreValidator() {
        av = new AbsoluteValidator();
        SpaceIgnoreValidator siv = new SpaceIgnoreValidator(av);
        assert siv.valid("test", "test ");
    }

    /**
     * Tests the combination of all validators.
     * Ensures that the valid method returns true when the strings are the same, ignoring case and spaces.
     */
    @Test
    void testAllValidators() {
        av = new AbsoluteValidator();
        CasseIgnoreValidator civ = new CasseIgnoreValidator(av);
        SpaceIgnoreValidator siv = new SpaceIgnoreValidator(civ);
        assert siv.valid("test", "TEST ");
    }

    /**
     * Tests the addValidator method in ProblemBuilder.
     * Ensures that the correct validator is set in the Problem object.
     */
    @Test
    void testAddValidator() {
        ProblemBuilder problemBuilder = new ProblemBuilder();

        problemBuilder.addValidator(CasseIgnoreValidator.class);
        assertTrue(problemBuilder.getProblem().getValidator() instanceof CasseIgnoreValidator);

        problemBuilder.addValidator(SpaceIgnoreValidator.class);
        assertTrue(problemBuilder.getProblem().getValidator() instanceof SpaceIgnoreValidator);
    }
}