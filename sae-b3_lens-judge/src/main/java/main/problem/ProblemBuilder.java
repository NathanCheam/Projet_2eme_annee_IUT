package main.problem;

import main.FileManager;
import main.problem.constraint.IContrainte;
import main.process.verification.DecoValidator;
import main.process.verification.IValidator;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;

/**
 * Class for building a Problem object.
 * This class provides methods to add constraints and test cases to a Problem object,
 * and to build the final Problem object.
 *
 * @author Cheam Nathan
 * @author Derancourt Louis
 */
public class ProblemBuilder {

    /**
     * The problem to build.
     */
    private final Problem problem;

    /**
     * Constructs a new ProblemBuilder and initializes the Problem object.
     */
    public ProblemBuilder() {
        this.problem = new Problem();
    }

    public Problem getProblem() {
        return problem;
    }


    /**
     * Searches in a folder for all files with the extension ".in" and ".ans".
     *
     * @param folder the folder to search in
     * @return a list of TestCase objects
     */
    ArrayList<TestCase> searchInFile(File folder) {
        // Répertorier tous les fichiers ".in" et ".ans" du dossier mis en paramètre
        ArrayList<TestCase> testCases = new ArrayList<>(); // liste qui va être retournée

        // si il s'agit d'un fichier (seul)
        if (!folder.isDirectory()) return testCases;

        // lister le contenue du dossier
        File[] files = folder.listFiles();

        // pour chaque fichier du dossier
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                testCases.addAll(searchInFile(file));
            } else if (file.isFile() && FileManager.isExtension(file, "in")) {
                // on stocke le nom du fichier mais sans l'extension
                String name = file.getName();
                name = name.substring(0, name.length() - 3);
                name = name + ".ans";

                // on cherche le ".ans" correspondant
                for (File file2 : files) {
                    if (file2.isFile() && file2.getName().equals(name)) {
                        testCases.add(new TestCase(file, file2));
                        break;
                    }
                }
            }
        }

        return testCases;
    }

    /**
     * Adds a constraint to the Problem object.
     *
     * @param contrainte the constraint to add
     */
    public void addContrainte(IContrainte contrainte) {
        this.problem.getContraintes().add(contrainte);
    }

    /**
     * Adds test cases from a file or directory to the Problem object.
     *
     * @param file the file or directory containing the test cases
     */
    public void addTestCase(File file) {
        this.problem.getTestCases().addAll(searchInFile(file));
    }

    /**
     * Builds and returns the Problem object.
     *
     * @return the built Problem object
     */
    public Problem build() {
        return this.problem;
    }

    /**
     * Adds a new validator to the problem.
     * The validator is created using the provided class and the current validator.
     *
     * @param <T> the type of the validator, which must extend DecoValidator
     * @param c the class of the validator to add
     */
    public <T extends DecoValidator> void addValidator(Class<T> c) {
        try {
            this.problem.setValidator(c.getDeclaredConstructor(IValidator.class).newInstance(this.problem.getValidator()));
        } catch (Exception e) {
            throw new UncheckedIOException(new IOException("Error while adding validator", e));
        }
    }
}