package scenario_two;

import java.util.Map;
import java.util.Random;

public class PatientArrival implements Runnable{

    private final Map<String,PatientQueue> queues;
    private final Random random;
    private volatile boolean running=true;
    private int patientCounter=1;

    public PatientArrival(Map<String,PatientQueue> queues){
        this.queues=queues;
        this.random=new Random();
    }

    @Override
    public void run() {
        try{
            while(running){
                Thread.sleep(300);

                // Random speciality selection
                String speciality = pickSpeciality();

                Patient patient =new Patient(patientCounter++, speciality);

                System.out.println("[ARRIVAL] " + patient);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private String pickSpeciality() {
        int value = random.nextInt(3);
        return value == 0 ? "Cardiology"
                : value == 1 ? "Neurology"
                : "Orthopaedics";
    }

    public void stop() {
        running = false;
    }


}
