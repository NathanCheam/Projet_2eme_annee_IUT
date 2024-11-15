package main.problem.constraint;

/**
 * Interface representing a constraint that can be applied to a process.
 * @author Notteau Romain
 */
public interface IContrainte {
    /**
     * Runs the given process with the constraint applied.
     *
     * @param process the process to be run with the constraint
     */
    void run(Process process);
}