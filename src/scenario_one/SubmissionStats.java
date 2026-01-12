package scenario_one;

import java.util.concurrent.atomic.AtomicInteger;

public class SubmissionStats {

    // Atomic counters guarantee correctness under concurrency
    AtomicInteger successCount=new AtomicInteger(0);
    AtomicInteger failureCount=new AtomicInteger(0);

    public void recordSuccess(){
        successCount.incrementAndGet();
    }

    public void recordFailure(){
        failureCount.incrementAndGet();
    }

    public int getTotalProcessed(){
        return successCount.get() + failureCount.get();
    }

    public double getSuccessRate() {
        int total = getTotalProcessed();
        return total == 0 ? 0.0 : (successCount.get() * 100.0) / total;
    }

    /**
     * Display aggregated statistics
     */
    public void display(long executionTimeMs){
        System.out.println("\n"+"=".repeat(60));
        System.out.println("===========================================================");
        System.out.println("              SUBMISSION SYSTEM STATISTICS                 ");
        System.out.println("===========================================================");
        System.out.printf("Total Students Processed: %,d%n",getTotalProcessed());
        System.out.printf("Successful Submissions   : %,d%n", successCount.get());
        System.out.printf("Failed Submissions       : %,d%n", failureCount.get());
        System.out.printf("Success Rate             : %.2f%%%n", getSuccessRate());
        System.out.printf("Total Execution Time     : %.2f seconds%n", executionTimeMs / 1000.0);
        System.out.println("=".repeat(60));
    }

}
