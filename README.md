# Eastminster Concurrent Systems

This is a coursework project implementing concurrent programming principles through real-world scenario simulations. The code demonstrates practical applications of thread management, synchronization, and concurrent design patterns in Java.

## Overview

The project contains two distinct scenarios that explore different aspects of concurrent programming. Each scenario models a real-world system where multiple threads interact and coordinate to process tasks efficiently.

## Project Structure

```
src/
├── scenario_one/       University submission processing system
└── scenario_two/       Hospital A&E simulation
```

## Scenario One: University Submission System

The submission system simulates processing student coursework submissions concurrently.

**What it demonstrates:**
- Multiple student submissions arriving and being processed in parallel
- Using thread pools to manage worker threads efficiently
- Coordinating thread completion with synchronization barriers
- Collecting statistics from concurrent operations without race conditions

**How it works:**
Students submit their coursework through a system that assigns processing tasks to available worker threads. Each submission takes a variable amount of time to process and may succeed or fail. The system tracks how many submissions succeeded and failed, calculating overall statistics.

**Key concurrent features:**
- ExecutorService with a fixed thread pool sized to available CPU cores
- CountDownLatch to wait for all submissions to complete before displaying results
- AtomicInteger for thread-safe counters that avoid locks and contention
- Immutable Student objects passed between threads without synchronization overhead

**Learning outcomes:**
- How thread pools handle task scheduling and worker thread reuse
- Why atomic operations are better than synchronized blocks for simple counters
- How barriers synchronize multiple threads and coordinate completion
- Techniques for gathering metrics from parallel operations safely

## Scenario Two: Hospital A&E Simulation

The hospital system simulates the Accident & Emergency department managing patient flow across different specialities.

**What it demonstrates:**
- Producer-consumer pattern with separate threads for patient arrivals and treatment
- Managing multiple queues for different specialities concurrently  
- Thread-safe blocking queues handling synchronization automatically
- Shift management coordinating consultant teams

**How it works:**
Patients continuously arrive (producer) and are assigned to speciality-specific queues. Consultants work shifts (consumers), taking patients from queues and treating them. The system cycles through day and night shifts. When a shift ends, the consultant thread stops and hands over to the next shift's consultant.

**Key concurrent features:**
- PatientArrival thread continuously generates patients at random intervals
- BlockingQueue handles all synchronization between producers and consumers
- Multiple Consultant threads work simultaneously on different specialities
- ShiftManager orchestrates consultant teams and manages shift transitions
- Immutable Patient objects ensuring no accidental shared state modification

**Learning outcomes:**
- How the producer-consumer pattern separates data creation from processing
- Why BlockingQueue eliminates manual lock management and condition variables
- How to coordinate multiple worker threads on shared resources
- Techniques for managing thread lifecycle during system handovers
- Handling thread interruption gracefully during shift changes

## Running the Project

**Prerequisites:**
- Java 11 or higher
- Standard command line tools

**Scenario One - Submission System:**
```bash
cd src
javac scenario_one/*.java
java scenario_one.SubmissionSystem
```
Follow the prompt to enter the number of students to simulate. The system will process all submissions and display statistics.

**Scenario Two - Hospital Simulation:**
```bash
cd src
javac scenario_two/*.java
java scenario_two.HospitalSimulation
```
The system runs the entire simulation automatically, showing a day/night cycle with shift handovers.

## Key Concepts Covered

**Thread Safety:** Both scenarios use immutable objects (Student, Patient) to safely share data between threads without synchronization overhead.

**Synchronization:** 
- Scenario one uses AtomicInteger for lock-free counters
- Scenario two uses BlockingQueue which handles all locking internally

**Coordination:**
- Scenario one uses CountDownLatch to wait for completion
- Scenario two uses thread interruption and shift management for lifecycle control

**Concurrency Patterns:**
- Scenario one: Thread pool pattern with executor services
- Scenario two: Producer-consumer pattern with multiple consumers

## Design Decisions

Both scenarios favor immutability and thread-safe library classes over manual locking. This reduces the risk of deadlocks and race conditions while keeping the code readable. The blocking queue, atomic types, and countdown latch handle synchronization, allowing the business logic to remain clean and straightforward.

---

Author: Dumindu Induwara Gamage (20221168)
Course: 5SENG003C.2 Concurrent Programming Coursework
University: University of Westminster
