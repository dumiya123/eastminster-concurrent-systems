package scenario_one;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread-safe statistics tracker for submission operations.
 * Uses AtomicInteger for lock-free concurrent updates.
 * Concurrency Mechanism: AtomicInteger
 */

public class SubmissionStats {

    // AtomicInteger provides thread-safe increment operations without locks
    // These counters will be updated by multiple threads concurrently
    private final AtomicInteger successCount;
    private final AtomicInteger failureCount;
    private final AtomicInteger totalProcessed;

    public SubmissionStats() {
        this.successCount = new AtomicInteger(0);
        this.failureCount = new AtomicInteger(0);
        this.totalProcessed = new AtomicInteger(0);
    }

    /**
     * Records a successful submission.
     * Thread Safety: This method is called by multiple SubmissionProcessor threads.
     * incrementAndGet() is atomic - guarantees no lost updates even under concurrent access.
     * Atomicity: The operation "read current value, add 1, write new value" happens
     * as a single indivisible operation. No other thread can see intermediate state.
     */
    public void recordSuccess(){
        successCount.incrementAndGet();
        totalProcessed.incrementAndGet();
    }

    /**
     * Records a failed submission
     * Thread Safety: Same atomicity guarantees as recordSuccess().
     * Multiple threads can call this simultaneously without data corruption.
     */
    public void recordFailure(){
        failureCount.incrementAndGet();
        totalProcessed.incrementAndGet();
    }

    /**
     * Get the current count of successful submissions.
     * @return current success count (thread safe read)
     */
    public int getSuccessCount(){
        return successCount.get();
    }

    /**
     * Get the current count of failure submissions.
     * @return current failure count (thread safe read)
     */
    public int getFailureCount(){
        return failureCount.get();
    }

    /**
     * Get the total number of processed submissions.
     * @return total number of processed (thread safe read)
     */
    public int getTotalProcessed(){
        return totalProcessed.get();
    }

    /**
     * Calculates the success rate as a percentage.
     * Note: This calculation is NOT atomic - it reads three separate AtomicIntegers.
     * For display purposes, this is acceptable. For critical decisions, would need
     * additional synchronization.
     * @return Success rate percentage (0.0 to 100.0)
     */
    public double getSuccessRate() {
        int total = totalProcessed.get();
        if (total == 0) {
            return 0.0;
        }
        // Calculate percentage: (success / total) * 100
        return (successCount.get() * 100.0) / total;
    }

    /**
     * Displays comprehensive statistics report to console.
     * Called after all submissions complete - no concurrency issues.
     * @param totalTimeMs Total execution time in milliseconds
     */
    public void displayStats(long totalTimeMs) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           SUBMISSION SYSTEM STATISTICS");
        System.out.println("=".repeat(60));

        // % d formats numbers with a thousand separators (e.g., 100,000)
        System.out.printf("Total Students Processed : %,d%n", getTotalProcessed());
        System.out.printf("Successful Submissions   : %,d%n", getSuccessCount());
        System.out.printf("Failed Submissions       : %,d%n", getFailureCount());
        System.out.printf("Success Rate             : %.2f%%%n", getSuccessRate());
        System.out.printf("Total Execution Time     : %.2f seconds%n", totalTimeMs / 1000.0);

        // Calculate average processing time per student
        double avgTime = getTotalProcessed() > 0 ?
                (double) totalTimeMs / getTotalProcessed() : 0;
        System.out.printf("Average Time per Student : %.2f ms%n", avgTime);

        System.out.println("=".repeat(60));
    }


}
