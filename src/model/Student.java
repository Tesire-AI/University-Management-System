package model;

import java.util.*;

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

    public List<Course> getEnrolledCourses(){
        return Collections.unmodifiableList(enrolledCourses);
    }
    public Map<Course, Double> getCompletedCourses(){
        return Collections.unmodifiableMap(completedCourses);
    }

    public void addEnrolledCourse(Course course){
        if(course== null){
            throw new IllegalArgumentException("Course can't be empty");
        }
        if(completedCourses.containsKey(course)){
            throw new IllegalArgumentException("This course was completed");
        }
        if(!enrolledCourses.contains(course)){
            enrolledCourses.add(course);
        }
    }

    public void removeEnrolledCourse(Course course){
        enrolledCourses.remove(course);
        }
    public void addCompletedCourses(Course course, double marks){
        if(course== null){
            throw new IllegalArgumentException("Course can't be empty");
        }
        if(marks<0 || marks> 100){
            throw new IllegalArgumentException("Marks should be between 0 and 100");
        }
            completedCourses.put(course,marks);
            enrolledCourses.remove(course);
        }

    protected double marksToGradePoints(double marks) {
        if (marks >= 90) return 4.0;
        if (marks >= 80) return 3.0;
        if (marks >= 70) return 2.0;
        if (marks >= 60) return 1.0;
        return 0.0;
    }

    public int getTotalCreditsEarned() {
        int total = 0;
        for (Course course : completedCourses.keySet()) {
            total += course.getCredits();
        }
        return total;
    }

    public abstract double calculateGPA(); // to calculate the gpa
    public abstract boolean isEligibleToGraduate(); //to see if he is a graduate student already or not


    @Override
    public String toString() {

        return "Student{" +
                "enrolled Courses=" + enrolledCourses +
                ", completed Courses=" + completedCourses +
                '}';
    }
}
