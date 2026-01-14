package scenario_two;

/*
 COPYRIGHT (C) Dumindu Induwara Gamage-20221168-w1953846-dumindu.20221168@iit.ac.lk. All Rights Reserved.
 5SENG003C.2 Concurrent Programming Coursework L6 sem 1
 @author Dumindu Induwara Gamage.
 @version 1 Hospital Simulation.
 */

import java.time.LocalDateTime;

/**
 * Immutable domain object representing a patient.
 * Safe to share across threads.
 */
public final class Patient {

    private final int patientId; // Unique patient identifier
    private final String speciality; // Required speciality
    private final LocalDateTime arrivalTime; // Time patient arrived

    // Constructor to create a patient
    public Patient(int patientId, String speciality) {
        this.patientId = patientId;
        this.speciality = speciality; // Assign speciality
        this.arrivalTime=LocalDateTime.now();
    }

    // Getter for patient ID
    public int getPatientId(){
        return patientId;
    }

    public String getSpeciality(){
        return speciality;
    }

    public LocalDateTime getArrivalTime(){
        return arrivalTime;
    }

    // Clean string representation (no timestamps here)
    @Override
    public String toString() {
        return String.format("Patient #%04d",
                patientId
        );
    }



}
