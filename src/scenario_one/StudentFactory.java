package scenario_one;

/**
 * Factory for creating validated Student objects.
 * Separates validation logic from Student class (Factory Pattern).
 *
 * @author Dumindu Induwara Gamage - 20221168
 */
public class StudentFactory {

    private static final int MIN_ID_LENGTH = 3;
    private static final int MAX_ID_LENGTH = 20;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 100;

    private StudentFactory() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    /**
     * Creates validated Student object.
     * @param studentId Student identifier
     * @param name Student name
     * @return Validated Student instance
     * @throws IllegalArgumentException if validation fails
     */
    public static Student createStudent(String studentId, String name) {
        String validatedId = validateAndTrimId(studentId);
        String validatedName = validateAndTrimName(name);
        return new Student(validatedId, validatedName);
    }

    /**
     * Creates Student with sequential ID format.
     * @param sequenceNumber Sequential number for ID
     * @param name Student name
     * @return Validated Student instance
     */
    public static Student createStudentWithSequence(int sequenceNumber, String name) {
        String generatedId = String.format("STU%05d", sequenceNumber);
        return createStudent(generatedId, name);
    }

    /**
     * Validates and trims student ID.
     */
    private static String validateAndTrimId(String studentId) {
        validateNotNull(studentId, "Student ID");
        String trimmed = studentId.trim();
        validateNotEmpty(trimmed, "Student ID");
        validateLength(trimmed, "Student ID", MIN_ID_LENGTH, MAX_ID_LENGTH);
        return trimmed;
    }

    /**
     * Validates and trims student name.
     */
    private static String validateAndTrimName(String name) {
        validateNotNull(name, "Student name");
        String trimmed = name.trim();
        validateNotEmpty(trimmed, "Student name");
        validateLength(trimmed, "Student name", MIN_NAME_LENGTH, MAX_NAME_LENGTH);
        return trimmed;
    }

    /**
     * Validates value is not null.
     */
    private static void validateNotNull(String value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
    }

    /**
     * Validates value is not empty.
     */
    private static void validateNotEmpty(String value, String fieldName) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
    }

    /**
     * Validates string length is within range.
     */
    private static void validateLength(String value, String fieldName, int min, int max) {
        int length = value.length();
        if (length < min || length > max) {
            throw new IllegalArgumentException(
                    String.format("%s must be %d-%d characters (got %d)",
                            fieldName, min, max, length)
            );
        }
    }
}