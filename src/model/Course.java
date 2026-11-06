package model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseCode;
    private String courseName;
    private Instructor instructor;


    private List<Student> studentRoster = new ArrayList<>();

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public void addStudentToRoster(Student student) {
        if (!studentRoster.contains(student)) {
            studentRoster.add(student);
        }
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
        instructor.assignCourse(this);
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }
    public String getCourseName() {
        return courseName;
    }
    public Instructor getInstructor() {
        return instructor;
    }
    public List<Student> getStudentRoster() {
        return studentRoster;
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseName;
    }
}