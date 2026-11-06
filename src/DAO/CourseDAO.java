package DAO;

import model.Course;
import java.util.List;

public interface CourseDAO {
    void addCourse(Course course);

    void removeCourse(String courseCode);

    void updateCourse(Course course);

    Course getCourseByCode(String courseCode);

    List<Course> getAllCourses();
}