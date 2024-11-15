package main.problem;

import java.io.File;

/**
 * The TestCase class represents a test case with input and expected answer files,
 * and a generic output type.
 *
 * @author Fauquet Sacha
 */
public class TestCase {
    private final File in;
    private final File ans;

    /**
     * Constructs a new TestCase with the specified input and answer files.
     *
     * @param in  the input file
     * @param ans the answer file
     */
    public TestCase(File in, File ans) {
        this.in = in;
        this.ans = ans;
    }

    // GETTERS

    /**
     * Returns the input file of the test case.
     *
     * @return the input file
     */
    public File getIn() {
        return in;
    }

    /**
     * Returns the answer file of the test case.
     *
     * @return the answer file
     */
    public File getAns() {
        return ans;
    }
}