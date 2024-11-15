package main.process;

import main.Judge;
import main.Result;
import main.problem.Problem;
import main.problem.TestCase;
import main.problem.constraint.IContrainte;
import main.process.verification.IValidator;

import java.io.*;
import java.util.List;

/**
 * Interface for the compilers
 * @author Derancourt Louis
 */
public interface IProcess {

    /**
     * Compile the program
     * @param judge The Judge instance used to set the result of the compilation.
     * @param sourceFile The source file to be compiled.
     */
    File compile(Judge judge, File sourceFile);

    /**
     * Execute the program
     * @param judge The Judge instance used to set the result of the execution.
     * @param compiledFile The compiled file to be executed.
     * @param problem The problem to be solved.
     */
    default void execute(Judge judge, File compiledFile, Problem problem) {
        for (TestCase testCase : problem.getTestCases()) {
            executeTest(judge, compiledFile, testCase, problem.getContraintes(), problem.getValidator());
        }
    }

    /**
     * Execute un test
     * @param judge The Judge instance used to set the result of the execution.
     * @param compiledFile The compiled file to be executed.
     * @param testCase The test case to be executed.
     * @param contraintes The constraints to be checked.
     * @param validator The validator to be used.
     */
    void executeTest(Judge judge, File compiledFile, TestCase testCase, List<IContrainte> contraintes, IValidator validator);


    default void executeProcess(Judge judge, File compiledFile, ProcessBuilder builder, TestCase testCase, List<IContrainte> contraintes, IValidator validator) {

        // Create the process
        Process process;
        // Handle the output of the process

        try {
            process = builder.start();

            for (IContrainte contrainte : contraintes) {
                contrainte.run(process);
            }

            // Handle the output of the process
            BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));


            // Lecteur
            judge.getLector().apply(judge, validator, testCase, process, outputReader);

            process.waitFor();

            outputReader.close();

        } catch (IOException e) {
            judge.setResult(new Result(false, "Error, impossible to start the compilation for the file " + compiledFile.getName()));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            judge.setResult(new Result(false, "Error, impossible to execute the file " + compiledFile.getName()));
        }

    }

}
