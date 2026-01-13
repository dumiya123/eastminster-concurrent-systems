package scenario_two;

import java.util.concurrent.BlockingQueue;

public class PatientQueue {
    private final BlockingQueue<Patient> queue;

    public PatientQueue(BlockingQueue<Patient> queue) {
        this.queue = queue;
    }

    public void addPatient(Patient patient) throws InterruptedException{
        queue.put(patient);
    }

    public Patient takePatient() throws InterruptedException{
        return queue.take();
    }
}
