package scenario_one;

/*
 COPYRIGHT (C) Dumindu Induwara Gamage-20221168-w1953846-dumindu.20221168@iit.ac.lk. All Rights Reserved.
 5SENG003C.2 Concurrent Programming Coursework L6 sem 1
 @author Dumindu Induwara Gamage.
 @version 1 University Submission Simulation.
 */

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

    /**
     * Executes student submissions concurrently and waits for completion.
     */
    public void processSubmissions() {

        // Holds all student submission requests
        List<Student> students = new ArrayList<>(numberOfStudents);

        // Create independent student tasks (no shared mutable state)
        for (int i = 1; i <= numberOfStudents; i++) {
            students.add(new Student("STU" + i, "Student_" + i));
        }

        // Synchronisation barrier to detect when all tasks finish
        CountDownLatch latch = new CountDownLatch(students.size());

        // Thread pool sized for CPU-bound parallelism
        int poolSize = Runtime.getRuntime().availableProcessors() * 2;

        // Manages worker threads and task scheduling
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        // Measure total concurrent execution time
        long start = System.currentTimeMillis();

        // Submit all submissions for parallel execution
        for (Student student : students) {
            executor.execute(new SubmissionTask(student, stats, latch));
        }

        // Prevent new tasks while allowing existing ones to complete
        executor.shutdown();

        try {
            // Block until every submission signals completion
            latch.await();
        } catch (InterruptedException e) {
            // Preserve interruption contract
            Thread.currentThread().interrupt();
        }

        // Capture completion time after all threads finish
        long end = System.currentTimeMillis();

        // Display aggregated, thread-safe statistics
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

