package scenario_one;

/*
 COPYRIGHT (C) Dumindu Induwara Gamage-20221168-w1953846-dumindu.20221168@iit.ac.lk. All Rights Reserved.
 6SENG006W Concurrent Programming  L6 sem 1
 @author Dumindu Induwara Gamage.
 @version Scenario 1 :Submission System
 */

import java.util.Random;

public class Student {

    private final int studentId;
    private final String name;
    private final Random random;


    public Student(int studentId,String name,Random random){
        this.studentId=studentId;
        this.name=name;
        this.random=random;
    }

    public int getStudentId(){
        return studentId;
    }

    public String getName() {
        return name;
    }

    public Random getRandom() {
        return random;
    }





}
