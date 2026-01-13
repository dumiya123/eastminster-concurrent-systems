package scenario_one;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main concurrent submission system.
 */
public class SubmissionSystem {

    private final int numberOfStudents;
    private final SubmissionStats stats = new SubmissionStats();

    public SubmissionSystem(int numberOfStudents) {
        if (numberOfStudents <= 0) {
            throw new IllegalArgumentException("Invalid student count");
        }
        this.numberOfStudents = numberOfStudents;
    }

    /**
     * Thread-safe printing method.
     */
    public static synchronized void printResult(String message) {
        System.out.println(message);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void processSubmissions() {

        List<Student> students = new ArrayList<>(numberOfStudents);
        for (int i = 1; i <= numberOfStudents; i++) {
            students.add(new Student("STU" + i, "Student_" + i));
        }

        CountDownLatch latch = new CountDownLatch(students.size());

        int poolSize = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        long start = System.currentTimeMillis();

        for (Student student : students) {
            executor.execute(new SubmissionTask(student, stats, latch));
        }
        executor.shutdown(); // graceful shutdown only

        try {
            latch.await();   // WAIT FOR ALL TASKS
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long end = System.currentTimeMillis();
        stats.display(end - start);
    }

    public static void main(String[] args) {

        System.out.println("""
            ========================================================
                 EASTMINSTER UNIVERSITY
                 Concurrent Submission System
            ========================================================
            """);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students to simulate: ");
        int studentCount = scanner.nextInt();

        System.out.println("""
            
            ------------------ SYSTEM CONFIGURATION ------------------
            Students to process : %d
            Logging             : Individual submission results
            Legend              : 🟢 Success   🔴 Failure
            -----------------------------------------------------------
            
            Starting submission processing...
            -----------------------------------------------------------
            """.formatted(studentCount));

        new SubmissionSystem(studentCount).processSubmissions();

        System.out.println("""
            -----------------------------------------------------------
            Processing completed successfully.
            -----------------------------------------------------------
            """);
    }
}

