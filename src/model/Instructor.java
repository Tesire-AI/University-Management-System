package model;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {
    private String department;


    private List<Course> coursesTaught = new ArrayList<>();

    public Instructor(String id, String firstname, String lastname, String email, String department) {
        super(id, firstname, lastname, email);
        this.department = department;
    }

    public void assignCourse(Course course) {
        coursesTaught.add(course);
    }

    public List<Course> getCoursesTaught() { return coursesTaught; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return super.toString() + ", Dept: " + department;
    }
}