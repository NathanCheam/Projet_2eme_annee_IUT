package main.process.verification;

import main.Judge;
import main.Result;
import main.problem.TestCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of the ILector interface that checks if all lines of the output match the expected answer lines.
 * @author Fauquet Sacha
 */
public class AllLector implements ILector {

    /**
     * Applies the lector to validate the output of a test case.
     *
     * @param judge the Judge instance to set the result
     * @param validator the IValidator instance to validate the output
     * @param testCase the TestCase instance containing the expected answer file
     * @param process the Process instance representing the running process
     * @param outputReader the BufferedReader instance to read the process output
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void apply(Judge judge, IValidator validator, TestCase testCase, Process process, BufferedReader outputReader) throws IOException {
        // Read all lines from the expected output file
        List<String> expectedLines = Files.readAllLines(Paths.get(testCase.getAns().toString()));
        Set<String> expectedSet = new HashSet<>(expectedLines);
        // Read all lines from the actual output file
        List<String> actualLines = outputReader.lines().toList();
        Set<String> seenLines = new HashSet<>();
        // Check if each line in the actual output exists in the expected set and has not appeared before
        for (String line : actualLines) {
            if (!expectedSet.contains(line) || !seenLines.add(line)) {
                judge.setResult(new Result(false, "Error: Output does not match expected output"));
                return;
            }
        }
        // Ensure the number of lines read is the same
        if (expectedLines.size() != actualLines.size()) {
            judge.setResult(new Result(false, "Error: Number of lines in output does not match expected output"));
        }
    }
}