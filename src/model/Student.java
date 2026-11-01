package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Student extends User {


    private List<Course> enrolledCourses;
    private Map<Course, Double> completedCourses;

    public Student(String ID, String firstName, String lastName, String email) {
        super(ID, firstName, lastName, email);

        // i initialised list and map inside the constructor so that they don't stay null but get
        //updated as a new student is added or whatever and to avoid the nullPointer exception
        this.enrolledCourses= new ArrayList<>();
        this.completedCourses= new HashMap<>();

    }

    public abstract double calculateGPA(); // to calculate the gpa
    public abstract boolean isEligibleToGraduate(); //to see if he is a graduate student already or not
    public abstract void printDegreeProgress(); //to generate a report

    public List<Course> getEnrolledCourses(){
        return enrolledCourses;
    }
    public Map<Course, Double> getCompletedCourses(){
        return completedCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "enrolledCourses=" + enrolledCourses +
                ", completedCourses=" + completedCourses +
                '}';
    }
}
