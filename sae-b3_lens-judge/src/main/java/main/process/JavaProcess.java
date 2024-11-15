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
 * A compiler for Java source files.
 * This class provides methods to compile and execute Java source files.
 * It implements the IProcess interface.
 *
 * @author Derancourt Louis
 * @author Notteau Romain
 * @author Fauquet Sacha
 */
public class JavaProcess implements IProcess {

    /**
     * Compiles a Java source file.
     *
     * @param judge      The Judge instance used to set the result of the compilation.
     * @param sourceFile The Java source file to be compiled.
     * @return The compiled file if the compilation is successful, null otherwise.
     * @throws IllegalArgumentException if the source file is not a Java file.
     * @throws RuntimeException         if there is an error during the compilation process.
     */
    @Override
    public File compile(Judge judge, File sourceFile) {
        // Check if the file extension is .java
        // effective only if the method is called by another program because the judge already checks the extension
        if (!sourceFile.getName().endsWith(".java")) {
            throw new IllegalArgumentException("The source file must be a .java file");
        }

        // Determine the name of the compiled file (.class)
        String sourceFilePath = sourceFile.getAbsolutePath();
        File sourceDir = sourceFile.getParentFile();
        String classFileName = sourceFilePath.substring(0, sourceFilePath.lastIndexOf(".")) + ".class";
        File compiledFile = new File(classFileName);

        // Create the ProcessBuilder to compile the Java source file
        ProcessBuilder builder = new ProcessBuilder("javac", "-d", sourceDir.getAbsolutePath(), sourceFile.getAbsolutePath());
        builder.redirectErrorStream(true);

        // Start the compilation process
        Process process;
        try {
            process = builder.start();
        } catch (IOException e) {
            judge.setResult(new Result(false, "Error, impossible to start the compilation for the file " + sourceFile.getName()));
            return null;
        }

        // Wait for the process to complete
        int exitCode;
        try {
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            judge.setResult(new Result(false, "Error, impossible to compile the file " + sourceFile.getName()));
            return null;
        }

        // Check if the compilation failed
        if (exitCode != 0) {
            judge.setResult(new Result(false, "Error, impossible to compile the file " + sourceFile.getName()));
            return null;
        }

        // Return the compiled file (.class)
        if (compiledFile.exists()) {
            return compiledFile;
        } else {
            judge.setResult(new Result(false, "Error, no compiled file found " + sourceFile.getName()));
            return null;
        }
    }

    /**
     * Executes the compiled Java file with the given test case and constraints.
     *
     * @param judge      The Judge instance used to set the result of the execution.
     * @param compiledFile The compiled Java file to be executed.
     * @param testCase   The test case to be used for the execution.
     * @param contraintes The list of constraints to be applied during the execution.
     */
    @Override
    public void executeTest(Judge judge, File compiledFile, TestCase testCase, List<IContrainte> contraintes, IValidator validator) {
        // Check if the file is a .class file
        if (!compiledFile.getName().endsWith(".class")) {
            judge.setResult(new Result(false, "The compiled file must be a .class file"));
            return;
        }

        // Get the class name from the compiled file path
        String classFilePath = compiledFile.getAbsolutePath();
        String className = classFilePath.substring(classFilePath.lastIndexOf(File.separator) + 1, classFilePath.lastIndexOf("."));

        // Create the ProcessBuilder to execute the Java class
        ProcessBuilder builder = new ProcessBuilder("java", "-cp", compiledFile.getParent(), className);
        builder.redirectErrorStream(true);
        builder.redirectInput(testCase.getIn());

        // Define the input file
        builder.redirectInput(testCase.getIn());

        executeProcess(judge, compiledFile, builder, testCase, contraintes, validator);
    }
}