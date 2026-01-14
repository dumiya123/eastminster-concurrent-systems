package scenario_two;

/**
 * Represents a consultant working during a specific shift.
 * Acts as a CONSUMER in the Producer–Consumer pattern.
 */
public class Consultant implements Runnable {

    // Consultant name
    private final String name;

    // Consultant speciality
    private final String speciality;

    // Assigned patient queue
    private final PatientQueue queue;

    // Shift this consultant belongs to
    private final ShiftType shiftType;

    /**
     * Constructs a Consultant.
     */
    public Consultant(String name, String speciality, PatientQueue queue, ShiftType shiftType) {
        this.name = name;           // Assign consultant name
        this.speciality = speciality; // Assign speciality
        this.queue = queue;         // Assign queue
        this.shiftType = shiftType; // Assign shift
    }

    @Override
    public void run() {
        try {
            // Continue working until interrupted by shift manager
            while (!Thread.currentThread().isInterrupted()) {

                // Take next patient safely (blocks if none available)
                Patient patient = queue.takePatient();

                // Log treatment start
                System.out.printf("🩺 TREATING | %-5s | %-12s | %-8s | Patient #%04d%n", shiftType, speciality, name, patient.getPatientId());
                System.out.println();

                // Simulate treatment duration
                Thread.sleep(1200);

                // Log treatment completion
                System.out.printf("✅ DONE      | %-5s | %-12s | Patient #%04d%n", shiftType, speciality, patient.getPatientId()
                );

            }
        } catch (InterruptedException e) {

            // Restore interrupt flag
            Thread.currentThread().interrupt();

            // Log shift handover
            System.out.printf("🔄 HANDOVER | %-5s | %-12s | %s%n", shiftType, speciality, name
            );

        }
    }
}
