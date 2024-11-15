package main.problem;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Test class for the ProblemBuilder.
 * @author Derancourt Louis
 */
class TestProblemBuilder {

    /**
     * Tests the searchInFile method of the ProblemBuilder.
     * Ensures that the method correctly searches for test cases in the specified folder.
     */
    @Test
    void testSearchInFile() {
        ProblemBuilder problemBuilder = new ProblemBuilder();
        File folder = new File("src/test/resources/data");

        // Check that the folder exists
        assert folder.exists();

        // Check that the folder is a directory
        assert folder.isDirectory();

        // Check that the folder contains files
        assert Objects.requireNonNull(folder.listFiles()).length > 0;

        // Search for test cases in the folder
        ArrayList<TestCase> testCases = problemBuilder.searchInFile(folder);

        // Check that the list of test cases is not empty
        assert !testCases.isEmpty();
    }
}