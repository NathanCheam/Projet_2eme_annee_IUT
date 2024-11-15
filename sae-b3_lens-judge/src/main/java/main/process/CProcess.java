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
 * The CCompiler class is used to compile C source files.
 * This class provides methods to compile and execute C source files.
 * It implements the IProcess interface.
 *
 * @author Fauquet Sacha
 * @author Notteau Romain
 * @author Cheam Nathan
 * @author Derancourt Louis
 */
public class CProcess implements IProcess {

    /**
     * Compiles a C source file.
     *
     * @param judge      The Judge instance used to set the result of the compilation.
     * @param sourceFile The C source file to be compiled.
     * @return The compiled file if the compilation is successful, null otherwise.
     * @throws IllegalArgumentException if the source file is not a C file.
     * @throws RuntimeException         if there is an error during the compilation process.
     */
    public File compile(Judge judge, File sourceFile) {
        // Check if the file extension is .c
        if (!sourceFile.getName().endsWith(".c")) {
            throw new IllegalArgumentException("The source file must be a .c file");
        }

        // Determine the name of the compiled file (executable)
        String sourceFilePath = sourceFile.getAbsolutePath();
        String executableFileName = sourceFilePath.substring(0, sourceFilePath.lastIndexOf(".")) + ".exe";
        File compiledFile = new File(executableFileName);

        // Create the ProcessBuilder to compile with gcc
        ProcessBuilder builder = new ProcessBuilder("gcc", "-x", "c", "-Wall", "-O2", "-static", "-pipe", "-lm", "-o", compiledFile.getAbsolutePath(), sourceFile.getAbsolutePath());
        // Redirect compiler errors and output to the console
        builder.redirectErrorStream(true);

        Process process;
        int exitCode = -1;
        try {
            // Start the compilation process
            process = builder.start();
            // Wait for the process to complete
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            judge.setResult(new Result(false, "Error, impossible to execute the file " + compiledFile.getName()));
        } catch (IOException e) {
            judge.setResult(new Result(false, "Error, impossible to start the compilation for the file " + sourceFile.getName()));
            return null;
        }

        // Check if the compilation failed
        if (exitCode != 0) {
            judge.setResult(new Result(false, "Error, impossible to compile the file " + sourceFile.getName()));
            return null;
        }

        // Return the compiled file (executable)
        if (compiledFile.exists()) {
            return compiledFile;
        } else {
            judge.setResult(new Result(false, "Error, no compiled file found " + sourceFile.getName()));
            return null;
        }
    }

    /**
     * Executes the compiled C file with the given test case and constraints.
     *
     * @param judge The Judge instance used to set the result of the execution.
     * @param compiledFile The compiled C file to be executed.
     * @param testCase The test case to be executed.
     * @param contraintes The list of constraints to be applied during the execution.
     */
    @Override
    public void executeTest(Judge judge, File compiledFile, TestCase testCase, List<IContrainte> contraintes, IValidator validator) {
        // Create the ProcessBuilder to execute the compiled file
        ProcessBuilder builder = new ProcessBuilder(compiledFile.getAbsolutePath());
        // Redirect the input and output streams
        builder.redirectErrorStream(true);

        // Define the input file
        builder.redirectInput(testCase.getIn());

        executeProcess(judge, compiledFile, builder, testCase, contraintes, validator);
    }
}