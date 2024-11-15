package main.process;

import main.Judge;
import main.Result;
import main.problem.TestCase;
import main.problem.constraint.IContrainte;
import main.process.verification.IValidator;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * A compiler for Python source files.
 * This class provides methods to compile and execute Python source files.
 * It implements the IProcess interface.
 *
 * @author Notteau Romain
 * @author Derancourt Louis
 */
public class PythonProcess implements IProcess {

    /**
     * Compiles (verifies the syntax of) a Python source file.
     *
     * @param judge      The Judge instance used to set the result of the compilation.
     * @param sourceFile The Python source file to be compiled.
     * @return The source file if the syntax verification is successful.
     * @throws IllegalArgumentException if the source file is not a Python file.
     * @throws RuntimeException         if there is an error during the syntax verification process.
     */
    @Override
    public File compile(Judge judge, File sourceFile) {
        // Check if the file extension is .py
        if (!sourceFile.getName().endsWith(".py")) {
            throw new IllegalArgumentException("The source file must be a .py file");
        }

        // Create the ProcessBuilder to verify the syntax with py_compile
        ProcessBuilder builder = new ProcessBuilder("python3", "-m", "py_compile", sourceFile.getAbsolutePath());

        // Redirect compiler errors and output to the console
        builder.redirectErrorStream(true);

        Process process;
        try {
            // Start the verification process
            process = builder.start();
        } catch (IOException e) {
            judge.setResult(new Result(false, "Error, impossible to start the compilation for the file " + sourceFile.getName()));
            return null;
        }

        int exitCode;
        try {
            // Wait for the process to complete
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            judge.setResult(new Result(false, "Error, impossible to compile the file " + sourceFile.getName()));
            return null;
        }

        // Check if the verification failed
        if (exitCode != 0) {
            judge.setResult(new Result(false, "Error, the file " + sourceFile.getName() + " contains syntax errors"));
        }
        return sourceFile;
    }

    /**
     * Executes the compiled Python file with the given test case and constraints.
     *
     * @param judge      The Judge instance used to set the result of the execution.
     * @param compiledFile The compiled Python file to be executed.
     * @param testCase   The test case to be used for the execution.
     * @param contraintes The list of constraints to be applied during the execution.
     */
    @Override
    public void executeTest(Judge judge, File compiledFile, TestCase testCase, List<IContrainte> contraintes, IValidator validator) {
        // Create the ProcessBuilder to execute the compiled file
        ProcessBuilder builder = new ProcessBuilder("python3", compiledFile.getAbsolutePath());
        // Redirect the input and output streams
        builder.redirectErrorStream(true);

        // Define the input file
        builder.redirectInput(testCase.getIn());

        executeProcess(judge, compiledFile, builder, testCase, contraintes, validator);
    }

}