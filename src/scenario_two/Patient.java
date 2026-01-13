package scenario_two;

import java.time.LocalDateTime;

public final class Patient {

    private final int patientId;
    private final String speciality;
    private final LocalDateTime arrivalTime;

    public Patient(int patientId, String speciality) {
        this.patientId = patientId;
        this.speciality = speciality;
        this.arrivalTime=LocalDateTime.now();
    }

    public int getPatientId(){
        return patientId;
    }

    public String getSpeciality(){
        return speciality;
    }

    public LocalDateTime getArrivalTime(){
        return arrivalTime;
    }

    @Override
    public String toString() {
        return "Patient{" +
               "patientId=" + patientId +
               ", speciality='" + speciality + '\'' +
               ", arrivalTime=" + arrivalTime +
               '}';
    }
}
