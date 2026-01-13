package scenario_one;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a single concurrent submission task.
 * Guarantees latch countdown even on failure.
 */
public class SubmissionTask implements Runnable {

    //Student being processed
    private final Student student;

    //Shared statistics collector
    private final SubmissionStats stats;

    //Latch coordinating task completion
    private final CountDownLatch latch;

    public SubmissionTask(Student student,SubmissionStats stats,CountDownLatch latch){
        this.student=student;
        this.stats=stats;
        this.latch=latch;
    }

    @Override
    public void run() {
        try{
            //simulate processing latency
            int delay=ThreadLocalRandom.current().nextInt(50,201);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //simulate success probability
            boolean success=ThreadLocalRandom.current().nextDouble() <0.85;

            if (success){
                stats.recordSuccess();
            }


        }catch (){

        }

    }
}

