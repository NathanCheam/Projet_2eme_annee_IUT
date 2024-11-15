package main;

/**
 * The DummyProcess class contains a main method that simulates a process
 * running indefinitely by putting the main thread to sleep for a very long time.
 * @author Notteau Romain
 */
public class DummyProcess {
    public static void main(String[] args) throws InterruptedException {
        // Sleep indefinitely
        Thread.sleep(Long.MAX_VALUE);
    }
}