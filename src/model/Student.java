package model;

import java.util.HashSet;
import java.util.Set;

public abstract class Student extends User {
    private String studentType;
    private double gpa;


    private Set<Course> enrolledCourses = new HashSet<>();

    public Student(String id, String firstname, String lastname, String email, String studentType) {
        super(id, firstname, lastname, email);
        this.studentType = studentType;
        this.gpa = 0.0;
    }




    public abstract double calculateGPA();



    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
        course.addStudentToRoster(this);
    }

    public Set<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
    public double getGpa() {
        return gpa;
    }
    public String getStudentType() {
        return studentType;
    }

    @Override
    public String toString() {
        return super.toString() + ", Type: " + studentType + ", GPA: " + gpa;
    }
}