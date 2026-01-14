package scenario_one;

/*
 COPYRIGHT (C) Dumindu Induwara Gamage-20221168-w1953846-dumindu.20221168@iit.ac.lk. All Rights Reserved.
 5SENG003C.2 Concurrent Programming Coursework L6 sem 1
 @author Dumindu Induwara Gamage.
 @version 1 University Submission Simulation.
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Single submission processing task.
 */
public class SubmissionTask implements Runnable {

    private final Student student;
    private final SubmissionStats stats;
    private final CountDownLatch latch;

    public SubmissionTask(Student student, SubmissionStats stats, CountDownLatch latch) {
        this.student = student;
        this.stats = stats;
        this.latch = latch;
    }

    @Override
    public void run() {

        boolean success = false;   // outcome decided exactly once

        try {
            // Simulated processing time
            int delay = ThreadLocalRandom.current().nextInt(50, 201);
            Thread.sleep(delay);

            // Decide outcome
            success = ThreadLocalRandom.current().nextDouble() < 0.85;

            // Record outcome ONCE
            if (success) {
                stats.recordSuccess();
                SubmissionSystem.printResult(
                        String.format(
                                "🟢 %-8s | %-10s | Processed Time: %3d ms | Status: SUCCESS",
                                student.getStudentId(),
                                student.getName(),
                                delay
                        )
                );
            } else {
                stats.recordFailure();
                SubmissionSystem.printResult(
                        String.format(
                                "🔴 %-8s | %-10s | Processed Time: %3d ms | Status: FAILED",
                                student.getStudentId(),
                                student.getName(),
                                delay
                        )
                );
            }
            // Throttle output slightly (safe)
            Thread.sleep(5);

        } catch (InterruptedException e) {
            // Interruption does NOT change the submission result
            // Outcome already recorded or a task was canceled
            Thread.currentThread().interrupt();
        } finally {
            // ALWAYS signal completion exactly once
            latch.countDown();
        }
    }
}



