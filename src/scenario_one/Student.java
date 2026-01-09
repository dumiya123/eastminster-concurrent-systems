package scenario_one;

/*
 COPYRIGHT (C) Dumindu Induwara Gamage-20221168-w19538462-dumindu.20221168@iit.ac.lk. All Rights Reserved.
 6SENG006W Concurrent Programming  L6 sem 1
 @author Dumindu Induwara Gamage.
 @version Scenario 1 :Submission System
 */

import java.time.LocalDateTime;
import java.util.Random;

/*
 * This class Represents a student submitting coursework in the university system.
 * This class is immutable to ensure thread safety when multiple threads
 * access student objects concurrently.
 *
 * Thread safety: Immutable class - all fields are final, and no setters exist.
 */
public class Student {

    // Final fields ensure immutability - cannot be changed after construction
    private final int studentId;
    private final String name;
    private final LocalDateTime submissionTime;

    /*
     * Constructs a new student with auto generated submission timestamp
     * The submission time is captured at the moment of object creation
     * @param studentId Unique identifier for the student
     * @param name Full name of the student
     */
    public Student(int studentId,String name){
        this.studentId=studentId;
        this.name=name;
        // LocalDateTime.now() captures the exact moment of submission
        // LocalDateTime is thread-safe and immutable
        this.submissionTime=LocalDateTime.now();
    }

    /**
     * Returns the unique student identifier
     * @return Student ID as a String
     */
    public int getStudentId(){
        return studentId;
    }

    /**
     * Returns the student's full name.
     * @return Student name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the exact time when the student submitted.
     * @return Submission representing submission time
     */
    public LocalDateTime getSubmissionTime() {
        return submissionTime;
    }

    /**
     * Returns a formatted string representation of the student.
     * Useful for logging and debugging
     *
     * @return Formatted student information
     */
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", submissionTime=" + submissionTime +
                '}';
    }
}
