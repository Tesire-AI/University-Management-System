package model;

import java.util.HashMap;
import java.util.Map;

public class UniversityManager {

    private Map<String, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();
    private Map<String, Instructor> instructors = new HashMap<>();

    public void addStudent(Student student) {
        students.put(student.getid(), student);
    }

    public void addCourse(Course course) {
        courses.put(course.getCourseCode(), course);
    }

    public void addInstructor(Instructor instructor) {
        instructors.put(instructor.getid(), instructor);
    }


    public void enrollStudent(String studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student != null && course != null) {
            student.enrollInCourse(course);
            System.out.println(student.getfirstName() + " successfully enrolled in " + course.getCourseName());
        } else {
            System.out.println("Error: Student or Course not found.");
        }
    }
}