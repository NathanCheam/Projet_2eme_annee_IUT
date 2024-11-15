package main.problem.constraint;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The TimeLimit class implements the IContrainte interface and provides a mechanism
 * to limit the execution time of a process. If the process exceeds the specified time limit,
 * it will be terminated.
 * @author Notteau Romain
 */
public class TimeLimit implements IContrainte {
    private final long timeLimitMillis;

    /**
     * Constructs a TimeLimit object with the specified time limit.
     *
     * @param timeLimitMillis the time limit in milliseconds
     */
    public TimeLimit(long timeLimitMillis) {
        this.timeLimitMillis = timeLimitMillis;
    }

    /**
     * Runs the process and enforces the time limit. If the process exceeds the time limit,
     * it will be terminated.
     *
     * @param process the process to be run with a time limit
     */
    @Override
    public void run(Process process) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                process.destroy();
            }
        };

        timer.schedule(task, timeLimitMillis);

        try {
            process.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            timer.cancel();
        }
    }
}