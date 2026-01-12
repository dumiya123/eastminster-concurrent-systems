package scenario_one;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * This class Represents a student submitting coursework in the university system.
 * This class is immutable to ensure thread safety when multiple threads
 * access student objects concurrently.
 * Thread safety: Immutable class - all fields are final, and no setters exist.
 */
public class Student {

    // Final fields ensure immutability - cannot be changed after construction
    private final String studentId;
    private final String name;
    private final LocalDateTime submissionTime;

    /**
     * Package-private constructor - only accessible by StudentFactory.
     * This prevents direct instantiation and enforces factory usage.
     * @param studentId Unique student identifier
     * @param name Student's full name
     */
    public Student(String studentId,String name){
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
    public String getStudentId(){
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
