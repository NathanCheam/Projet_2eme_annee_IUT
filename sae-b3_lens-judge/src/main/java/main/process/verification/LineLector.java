package main.process.verification;

import main.Judge;
import main.Result;
import main.problem.TestCase;

import java.io.*;

/**
 * Implementation of the ILector interface that checks if each line of the output matches the corresponding line of the expected answer.
 * @author Derancourt Louis
 */
public class LineLector implements ILector {

    /**
     * Applies the lector to validate the output of a test case line by line.
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
        String lineOut;
        // Cast the TestCase.ans File into a BufferedReader
        BufferedReader ansReader = new BufferedReader(new InputStreamReader(new FileInputStream(testCase.getAns())));
        String lineAns;
        while ((lineOut = outputReader.readLine()) != null && (lineAns = ansReader.readLine()) != null) {
            if (!validator.valid(lineOut, lineAns)) {
                judge.setResult(new Result(false, ("Error, the output doesn't match : \n ↳[" + lineOut + "]\n ↳[" + lineAns) + "]"));
                return;
            }
        }
        ansReader.close();
    }
}