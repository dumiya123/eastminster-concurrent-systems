package scenario_two;

/*
 COPYRIGHT (C) Dumindu Induwara Gamage-20221168-w1953846-dumindu.20221168@iit.ac.lk. All Rights Reserved.
 5SENG003C.2 Concurrent Programming Coursework L6 sem 1
 @author Dumindu Induwara Gamage.
 @version 1 Hospital Simulation.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Entry point for the Royal Manchester Hospital A&E simulation.
 */
public class HospitalSimulation {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("\n🏥 ROYAL MANCHESTER HOSPITAL – A&E DAY/NIGHT SIMULATION\n");

        // Create persistent patient queues
        Map<String, PatientQueue> queues = new HashMap<>();
        queues.put("Paediatrics", new PatientQueue());
        queues.put("Surgery", new PatientQueue());
        queues.put("Cardiology", new PatientQueue());

        // Start continuous patient arrivals
        PatientArrival producer = new PatientArrival(queues);
        Thread producerThread = new Thread(producer);
        producerThread.start();

        // Each shift = 12 seconds (12 simulated hours)
        ShiftManager manager = new ShiftManager(queues, 12000);

        // Run alternating DAY and NIGHT shifts
        manager.startSimulation(4);

        // Stop producer cleanly
        producer.stop();
        producerThread.interrupt();

        System.out.println(
                "\n🏁 SIMULATION COMPLETE – DAY/NIGHT ROTATION SUCCESSFUL\n"
        );
    }
}
