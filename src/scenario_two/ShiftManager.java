package scenario_two;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Manages automatic rotation between DAY and NIGHT shifts.
 * Consultants are started and stopped using thread interruption.
 */
public class ShiftManager {

    // Map of speciality queues (persistent across shifts)
    private final Map<String, PatientQueue> queues;

    // Duration of one simulated shift in milliseconds
    private final long shiftDurationMs;

    // Tracks the current shift type
    private ShiftType currentShift = ShiftType.DAY;

    /**
     * Constructs a ShiftManager.
     * @param queues patient queues per speciality
     * @param shiftDurationMs duration of one shift
     */
    public ShiftManager(Map<String, PatientQueue> queues, long shiftDurationMs) {
        this.queues = queues;                 // Assign queues
        this.shiftDurationMs = shiftDurationMs; // Assign shift duration
    }

    /**
     * Starts the simulation and alternates between DAY and NIGHT shifts.
     * @param totalShifts total number of shifts to simulate
     */
    public void startSimulation(int totalShifts)
            throws InterruptedException {

        // Loop through the required number of shifts
        for (int i = 1; i <= totalShifts; i++) {

            // Display shift header
            printShiftHeader(currentShift);

            // Start consultants for the current shift
            List<Thread> consultants = startConsultants(currentShift);

            // Allow the shift to run for its full duration
            Thread.sleep(shiftDurationMs);

            // Display shift ending message
            System.out.println("════════════ " + currentShift + " SHIFT ENDING ════════════");

            // Interrupt all consultant threads safely
            for (Thread t : consultants) {
                t.interrupt();
            }

            // Wait for all consultants to finish before handover
            for (Thread t : consultants) {
                t.join();
            }

            // Rotate shift (DAY → NIGHT, NIGHT → DAY)
            rotateShift();
        }
    }

    /**
     * Starts consultants for the given shift.
     * @param shiftType current shift type
     * @return list of consultant threads
     */
    private List<Thread> startConsultants(ShiftType shiftType) {

        // List to keep track of consultant threads
        List<Thread> threads = new ArrayList<>();

        // Start Paediatrician
        threads.add(start(new Consultant("Dr Patel", "Paediatrics", queues.get("Paediatrics"), shiftType)));

        // Start Surgeon
        threads.add(start(new Consultant("Dr Khan", "Surgery", queues.get("Surgery"), shiftType)));

        // Start Cardiologist
        threads.add(start(new Consultant("Dr Smith", "Cardiology", queues.get("Cardiology"), shiftType)));

        return threads;
    }

    /**
     * Starts a new thread for a runnable task.
     */
    private Thread start(Runnable task) {
        Thread t = new Thread(task); // Create new thread
        t.start();                   // Start execution
        return t;                    // Return thread reference
    }

    /**
     * Switches between DAY and NIGHT shifts.
     */
    private void rotateShift() {
        currentShift =
                (currentShift == ShiftType.DAY)
                        ? ShiftType.NIGHT
                        : ShiftType.DAY;
    }

    /**
     * Prints a formatted shift header.
     */
    private void printShiftHeader(ShiftType shiftType) {
        System.out.println("\n════════════════════════════════════════════════════");
        System.out.println("🏥 " + shiftType + " SHIFT STARTED (12-HOUR SIMULATION)");
        System.out.println("════════════════════════════════════════════════════");
    }
}
