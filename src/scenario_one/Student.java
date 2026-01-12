package scenario_one;

import javax.swing.text.DateFormatter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Immutable domain object representing a student submission.
 * Immutability guarantees thread safety without synchronization.
 */
public class Student {

    //Unique identifier of the student
    private final String studentId;

    //Student display name
    private final String name;

    //Submission timestamp
    private final Instant submissionTime;

    /**
     * Constructs a valid student object
     * @param studentId unique student identifier
     * @param name name of the student
     */
    public Student(String studentId,String name){
        //Validate inputs before object creation
        StudentValidator.validatestudent(studentId);
        StudentValidator.validateName(name);
        this.studentId=studentId;
        this.name=name;
        this.submissionTime=Instant.now(); // capture submission moment
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
    public Instant getSubmissionTime() {
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
        DateTimeFormatter formatter=
                DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
                        .withZone(ZoneId.systemDefault());
        return String.format(
                "Student[ID=%s, Name=%s, Time=%s]",
                studentId,
                name,
                formatter.format(submissionTime)
        );
    }
}
