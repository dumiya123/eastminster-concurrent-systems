package scenario_two;

public class Consultant implements Runnable{

    private final String name;
    private final String speciality;
    private final PatientQueue queue;

    public Consultant(String name, String speciality, PatientQueue queue) {
        this.name = name;
        this.speciality = speciality;
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            while(!Thread.currentThread().isInterrupted()){
                Patient patient=queue.takePatient();

                System.out.println("[TREATMENT START] "+name+" treating "+patient);
                //Simulate treatment time
                Thread.sleep(1000);
                System.out.println("[TREATMENT END] "+name+" completed patient "+patient.getPatientId());
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("[SHIFT END] " + name + " (" + speciality + ") leaving shift");
        }
    }

}
