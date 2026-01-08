package scenario_one;

/*
 COPYRIGHT (C) Dumindu Induwara Gamage-20221168-w1953846-dumindu.20221168@iit.ac.lk. All Rights Reserved.
 6SENG006W Concurrent Programming  L6 sem 1
 @author Dumindu Induwara Gamage.
 @version Scenario 1 :Submission System
 */

import java.util.Random;

public class Student {

    int studentId;
    String name;
    Random random;

    public Student(){

    }

    public Student(int studentId,String name,Random random){
        this.studentId=studentId;
        this.name=name;
        this.random=random;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

}
