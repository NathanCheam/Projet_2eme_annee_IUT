package main.process.verification;

import main.Judge;
import main.problem.TestCase;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Implementation of the ILector interface that checks if any line of the output matches any line of the expected answer.
 * @author Cheam Nathan
 */
public class OneLector implements ILector {

    private final Collection<String> ansContent;

    /**
     * Constructs a OneLector instance with an empty collection for storing expected answer lines.
     */
    public OneLector() {
        ansContent = new ArrayList<>();
    }

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
        boolean found = false;
        BufferedReader ansReader = new BufferedReader(new FileReader(testCase.getAns()));
        String line;
        while ((line = ansReader.readLine()) != null) {
            ansContent.add(line);
        }
        ansReader.close();

        while((line = outputReader.readLine()) != null) {
            if (ansContent.contains(line)) {
                found = true;
                break;
            }
        }
        if(!found) {
            judge.setResult(new main.Result(false, "Error, the output doesn't match"));
        }
    }
}