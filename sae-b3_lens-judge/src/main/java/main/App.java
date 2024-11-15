package main;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import main.process.verification.*;

import java.io.File;
import java.util.List;

/**
 * Main application class for processing command-line arguments and executing the Judge.
 * @author Notteau Romain
 */
@Parameters(separators = "=")
public class App {

    /**
     * List of parameters, expected to be the source file and test directory.
     */
    @Parameter(description = "Source file and test directory")
    private List<File> parameters;

    /**
     * Flag to ignore case verification.
     */
    @Parameter(names = { "-IgnoreCasse" }, description = "Ignore case verification")
    private boolean ignoreCase = false;

    /**
     * Flag to ignore space verification.
     */
    @Parameter(names = { "-IgnoreSpace" }, description = "Ignore space verification")
    private boolean ignoreSpace = false;

    /**
     * Flag to enable read line option.
     */
    @Parameter(names = "-readLine", description = "Read line option")
    private boolean readLine = false;

    /**
     * Flag to enable read for all option.
     */
    @Parameter(names = "-readForAll", description = "Read for all option")
    private boolean readForAll = false;

    /**
     * Flag to enable read one for all option.
     */
    @Parameter(names = "-readOneForAll", description = "Read one for all option")
    private boolean readOneForAll = false;

    /**
     * Main method to parse command-line arguments and execute the Judge.
     *
     * @param argv Command-line arguments
     */
    public static void main(String... argv) {
        App app = new App();
        JCommander commander = JCommander.newBuilder()
                .addObject(app)
                .build();

        try {
            commander.parse(argv);
            app.validateOptions();
        } catch (ParameterException e) {
            System.err.println(e.getMessage());
            commander.usage();
            return;
        }

        if (app.parameters == null || app.parameters.size() != 2) {
            System.err.println("Usage: <source file> <test directory>");
            return;
        }

        File sourceFile = app.parameters.get(0);
        File testDirectory = app.parameters.get(1);

        if (!sourceFile.exists() || !testDirectory.exists()) {
            System.err.println("Source file or test directory does not exist");
            return;
        }

        Judge judge = new Judge(sourceFile, testDirectory);

        if (app.ignoreCase) {
            judge.addValidator(CasseIgnoreValidator.class);
        }

        if (app.ignoreSpace) {
            judge.addValidator(SpaceIgnoreValidator.class);
        }

        if (app.readForAll) {
            judge.setLector(AllLector.class);
        }

        if (app.readLine) {
            judge.setLector(LineLector.class);
        }

        if (app.readOneForAll) {
            judge.setLector(OneLector.class);
        }

        System.out.println(judge.run().toString());
    }

    /**
     * Validates that only one of the mutually exclusive options is specified.
     *
     * @throws ParameterException if more than one option is specified
     */
    private void validateOptions() {
        int count = 0;
        if (readLine) count++;
        if (readForAll) count++;
        if (readOneForAll) count++;
        if (count > 1) {
            throw new ParameterException("Only one of -readLine, -readForAll, or -readOneForAll can be specified.");
        }
    }
}