package scenario_two;

/*
 COPYRIGHT (C) Dumindu Induwara Gamage-20221168-w1953846-dumindu.20221168@iit.ac.lk. All Rights Reserved.
 5SENG003C.2 Concurrent Programming Coursework L6 sem 1
 @author Dumindu Induwara Gamage.
 @version 1 Hospital Simulation.
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Thread-safe queue for one medical speciality.
 * Acts as the shared buffer in producer–consumer.
 */
public class PatientQueue {

    // Internal blocking queue
    private final BlockingQueue<Patient> queue; // Initialize queue

    /**
     * Default constructor.
     * Initializes the internal BlockingQueue.
     */
    public PatientQueue() {
        this.queue = new LinkedBlockingQueue<>();
    }

    // Adds a patient to the queue (producer)
    public void addPatient(Patient patient) throws InterruptedException {
        queue.put(patient); // Thread-safe insert
    }

    // Takes a patient from the queue (consumer)
    public Patient takePatient() throws InterruptedException {
        return queue.take(); // Blocks if empty
    }
}

