package scenario_one;

/*
 COPYRIGHT (C) Dumindu Induwara Gamage-20221168-w1953846-dumindu.20221168@iit.ac.lk. All Rights Reserved.
 5SENG003C.2 Concurrent Programming Coursework L6 sem 1
 @author Dumindu Induwara Gamage.
 @version 1 University Submission Simulation.
 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread-safe statistics collector.
 * Uses lock-free AtomicInteger counters to avoid contention.
 */
public class SubmissionStats {

    // Counter for successful submissions
    private final AtomicInteger successCount = new AtomicInteger(0);

    // Counter for failed submissions
    private final AtomicInteger failureCount = new AtomicInteger(0);

    /**
     * Records a successful submission.
     */
    public void recordSuccess() {
        successCount.incrementAndGet();
    }

    /**
     * Records a failed submission.
     */
    public void recordFailure() {
        failureCount.incrementAndGet();
    }

    /**
     * @return total number of processed submissions
     */
    public int getTotalProcessed() {
        return successCount.get() + failureCount.get();
    }

    /**
     * @return success rate percentage
     */
    public double getSuccessRate() {
        int total = getTotalProcessed();
        return total == 0
                ? 0.0
                : (successCount.get() * 100.0) / total;
    }

    /**
     * Displays aggregated statistics after all tasks complete.
     * @param executionTimeMs total execution time in milliseconds
     */
    public void display(long executionTimeMs) {

        System.out.println("\n" + "=".repeat(60));
        System.out.println("           SUBMISSION SYSTEM STATISTICS");
        System.out.println("=".repeat(60));
        System.out.printf("Total Students Processed : %,d%n", getTotalProcessed());
        System.out.printf("Successful Submissions   : %,d%n", successCount.get());
        System.out.printf("Failed Submissions       : %,d%n", failureCount.get());
        System.out.printf("Success Rate             : %.2f%%%n", getSuccessRate());
        System.out.printf("Total Execution Time     : %.2f seconds%n", executionTimeMs / 1000.0);
        System.out.println("=".repeat(60));
    }
}

