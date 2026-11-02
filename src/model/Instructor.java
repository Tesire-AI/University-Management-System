package model;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {

    private String department;
    private List<Course> courses;

    public Instructor(String ID, String firstName, String lastName, String email, String department) {
        super(ID, firstName, lastName, email);
        this.department = department;
        this.courses = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department == null || department.isBlank()) {
            throw new IllegalArgumentException("Department can't be empty");
        }
        this.department = department.trim();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void assignCourse(Course course) {
        if (course != null && !courses.contains(course)) {
            courses.add(course);
        }
    }

    // prints course codes only, so it never loops back into Course.toString()
    @Override
    public String toString() {
        List<String> codes = new ArrayList<>();
        for (Course c : courses) {
            codes.add(c.getCourseCode());
        }
        return "Instructor{" + super.toString() +
                ", department='" + department + '\'' +
                ", courses=" + codes +
                '}';
    }
}