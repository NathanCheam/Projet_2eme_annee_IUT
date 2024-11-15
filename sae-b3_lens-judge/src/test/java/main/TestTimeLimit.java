package main;

import main.problem.constraint.TimeLimit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the TimeLimit constraint.
 * @author Notteau Romain
 */
class TestTimeLimit {

    /**
     * Tests that a process is terminated after the specified time limit.
     */
    @Test
    void testProcessIsTerminatedAfterTimeLimit() {
        try {
            // Create a dummy process that runs indefinitely
            ProcessBuilder builder = new ProcessBuilder("java", "-cp", "src/test/java/main", "src/test/java/main/DummyProcess.java");
            Process process = builder.start();

            // Set a time limit of 1 second
            TimeLimit timeLimit = new TimeLimit(1000);

            // Measure the start time
            long startTime = System.currentTimeMillis();

            // Run the time limit constraint
            timeLimit.run(process);

            // Measure the end time
            long endTime = System.currentTimeMillis();

            // Calculate the elapsed time
            long elapsedTime = endTime - startTime;

            // Check if the process is terminated
            assertFalse(process.isAlive(), "Process should be terminated after time limit");

            // Print the elapsed time
            System.out.println("Elapsed time: " + elapsedTime + " ms");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred: " + e.getMessage());
        }
    }
}