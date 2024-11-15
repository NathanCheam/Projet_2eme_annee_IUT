package main.process.verification;

import main.Judge;
import main.problem.TestCase;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Interface for the Lector class.
 * @author Derancourt Louis
 */
public interface ILector {
    /**
     * Apply the verification process.
     */
    void apply(Judge judge, IValidator validator, TestCase testCase, Process process, BufferedReader outputReader) throws IOException;
}
