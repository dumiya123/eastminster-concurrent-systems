package scenario_one;

/**
 * Centralized validation logic for validating student related data
 * Separating validation improves reusability, testability,
 * and keeps domain objects clean.
 */
public class StudentValidator {

    //prevent instantiation
    private StudentValidator(){

    }

    /**
     * validates student ID
     * @param studentId student identifier
     */
    public static void validateStudentId(String studentId){
        if(studentId==null||studentId.isBlank()){
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        if (!studentId.matches("STU\\\\d{5}")){
            throw new IllegalArgumentException(
                    "Student ID must match pattern STU00000"
            );
        }
    }

    /**
     * validates student name
     * @param name student name
     */
    public static void validateStudentName(String name){
        if(name ==null||name.isBlank()){
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
        if(name.length()>50){
            throw new IllegalArgumentException(
                    "Student name length exceeds 50 characters"
            );
        }
    }

}
