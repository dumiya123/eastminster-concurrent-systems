package scenario_two;                             // Producer package

/*
 COPYRIGHT (C) Dumindu Induwara Gamage-20221168-w1953846-dumindu.20221168@iit.ac.lk. All Rights Reserved.
 5SENG003C.2 Concurrent Programming Coursework L6 sem 1
 @author Dumindu Induwara Gamage.
 @version 1 Hospital Simulation.
 */

import java.time.format.DateTimeFormatter; // Time formatting
import java.util.Map; // Queue mapping
import java.util.Random;// Random generator

/**
 * Continuously generates patients.
 * Acts as PRODUCER.
 */
public class PatientArrival implements Runnable {

    private final Map<String, PatientQueue> queues;// Speciality queues

    private final Random random = new Random(); // Random generator

    private volatile boolean running = true;// Control flag

    private int patientCounter = 1;// ID counter

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");         // Time format

    // Constructor
    public PatientArrival(Map<String, PatientQueue> queues) {
        this.queues = queues;                                // Assign queues
    }

    @Override
    public void run() {
        try {
            while (running) {
                // Run continuously
                Thread.sleep(400); // Random arrival gap

                String speciality = pickSpeciality();// Choose speciality

                Patient patient = new Patient(patientCounter++, speciality); // Create patient

                queues.get(speciality).addPatient(patient); // Add to queue

                System.out.printf("🟢 ARRIVAL   | %-12s | Patient #%04d | %s%n", speciality, patient.getPatientId(), patient.getArrivalTime().format(TIME_FORMAT)// Log arrival
                );
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt
        }
    }

    // Randomly select one valid speciality
    private String pickSpeciality() {
        int r = random.nextInt(3);
        return r == 0 ? "Paediatrics"
                : r == 1 ? "Surgery"
                : "Cardiology";
    }

    // Stop producer safely
    public void stop() {
        running = false;
    }
}

