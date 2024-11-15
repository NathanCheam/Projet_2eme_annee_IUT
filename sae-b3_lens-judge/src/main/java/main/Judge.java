package main;

import main.problem.Problem;
import main.problem.ProblemBuilder;
import main.problem.constraint.IContrainte;
import main.process.*;
import main.process.verification.DecoValidator;
import main.process.verification.ILector;
import main.process.verification.IValidator;
import main.process.verification.LineLector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Class of the project responsible for judging the execution of different programs against test cases.
 * It compiles, executes the program, and checks the results against the constraints.
 *
 * @author Derancourt Louis
 */
public class Judge {

    // ATTRIBUTES

    /**
     * List of all the judges.
     */
    private static final ArrayList<Judge> JUDGES = new ArrayList<>();

    /**
     * File for the input program.
     */
    private File inProg;

    /**
     * File for the input tests.
     */
    private File inTests;

    /**
     * Problem to solve.
     */
    private Problem problem;

    /**
     * List of the constraints.
     */
    private final ArrayList<IContrainte> constraint = new ArrayList<>();

    /**
     * Result of the execution.
     */
    private Result result;

    private final ArrayList<DecoValidator> validatorList = new ArrayList<>();

    private final List<IValidator> listeJudgeValidator = new ArrayList<>();

    private ILector lector;


    // CONSTRUCTORS

    /**
     * Constructor to initialize the Judge with input program and test files.
     *
     * @param inProg  The input program file.
     * @param inTests The input test file.
     */
    public Judge(File inProg, File inTests) {
        JUDGES.add(this);
        this.inProg = inProg;
        this.inTests = inTests;
        setLector(LineLector.class);
    }

    // METHODS

    /**
     * Execute the program by compiling and running it against the test cases.
     *
     * @return The result of the execution.
     */
    public Result run() {
        // Select the process object
        IProcess process = genProcess(inProg);
        // Assert that the process object is not null
        if (process == null) {
            setResult(new Result(false, "Error: Unsupported file extension"));
            return result;
        }

        // Compile the program
        File outProg = process.compile(this, inProg);
        // Assert that the compilation was successful
        if (outProg == null) {
            setResult(new Result(false, "Error: Compilation failed"));
            return result;
        }

        // Create the problem
        ProblemBuilder pb = new ProblemBuilder();
        pb.addTestCase(inTests);
        for (IContrainte c : constraint) {
            pb.addContrainte(c);
        }
        for (DecoValidator validator : validatorList){
            pb.addValidator(validator.getClass());
        }

        setProblem(pb.build());

        // Execute the program
        process.execute(this, outProg, problem);

        if (result == null || result.valid) {
            setResult(new Result(true, "Success"));
        }

        return result;
    }

    /**
     * Generate the process object for the program based on the file extension.
     *
     * @param inProg The input program file.
     * @return The process object.
     */
    public IProcess genProcess(File inProg) {
        return switch (FileManager.getFileExtension(inProg)) {
            case "c" -> new CProcess();
            case "cpp", "cxx", "cc" -> new CppProcess();
            case "java" -> new JavaProcess();
            case "py" -> new PythonProcess();
            default -> null;
        };
    }

    // SETTERS

    /**
     * Set the input program file.
     *
     * @param inProg The input program file.
     */
    public void setInProg(File inProg) {
        this.inProg = inProg;
    }

    /**
     * Set the input test file.
     *
     * @param inTests The input test file.
     */
    public void setInTests(File inTests) {
        this.inTests = inTests;
    }

    /**
     * Set the problem to solve.
     *
     * @param problem The problem to solve.
     */
    protected void setProblem(Problem problem) {
        this.problem = problem;
    }

    /**
     * Set the result of the execution.
     *
     * @param result The result of the execution.
     */
    public void setResult(Result result) {
        if (this.result == null) {
            this.result = result;
        } else {
            this.result = Result.merge(this.result, result);
        }
    }

    /**
     * Add a constraint to the list of constraints.
     *
     * @param constraint The constraint to add.
     */
    public void addConstraint(IContrainte constraint) {
        this.constraint.add(constraint);
    }

    /**
     * Remove a constraint from the list of constraints.
     *
     * @param constraint The constraint to remove.
     */
    public void removeConstraint(IContrainte constraint) {
        this.constraint.remove(constraint);
    }

    /**
     * Clear all constraints from the list.
     */
    public void clearConstraints() {
        constraint.clear();
    }

    // GETTERS

    /**
     * Get the result of the execution.
     *
     * @return The result of the execution.
     */
    public Result getResult() {
        return result;
    }

    /**
     * Adds a validator to the list of validators.
     * This method creates an instance of the specified validator class,
     * passing the last validator in the list as a parameter to the constructor.
     *
     * @param <T>    The type of the validator to be added.
     * @param classe The class of the validator to be added.
     */
    public <T extends DecoValidator> void addValidator(Class<T> classe) {
        try {
            IValidator lastValidator = listeJudgeValidator.isEmpty() ? null : listeJudgeValidator.get(listeJudgeValidator.size() - 1);
            T validatorInstance = classe.getDeclaredConstructor(IValidator.class).newInstance(lastValidator);
            validatorList.add(validatorInstance);
            listeJudgeValidator.add(validatorInstance);
        } catch (Exception e) {
            throw new IllegalArgumentException("Impossible to load the validator : " + classe.getName());
        }
    }

    /**
     * Gets the list of judge validators.
     *
     * @return The list of judge validators.
     */
    public List<IValidator> getListeJudgeValidator() {
        return listeJudgeValidator;
    }

    /**
     * Gets the list of validators.
     *
     * @return The list of validators.
     */
    public List<DecoValidator> getValidatorList(){
        return validatorList;
    }

    public void setLector(Class<? extends ILector> lector) {
        try {
            this.lector = lector.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Impossible to load the validator : " + lector.getName());
        }
    }

    public ILector getLector() {
        return lector;
    }
}