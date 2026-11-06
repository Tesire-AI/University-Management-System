package dao;

import DAO.CourseDAO;
import model.Course;
import model.Instructor;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDAO {

    @Override
    public void addCourse(Course course) {
        String sql = "INSERT INTO courses (course_code, course_name, instructor_id) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, course.getCourseCode());
            pstmt.setString(2, course.getCourseName());


            if (course.getInstructor() != null) {
                pstmt.setString(3, course.getInstructor().getid());
            } else {
                pstmt.setNull(3, Types.VARCHAR);
            }

            pstmt.executeUpdate();
            System.out.println("Course added: " + course.getCourseName());

        } catch (SQLException e) {
            System.err.println("Error adding course: " + e.getMessage());
        }
    }

    @Override
    public void removeCourse(String courseCode) {
        String sql = "DELETE FROM courses WHERE course_code = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, courseCode);
            pstmt.executeUpdate();
            System.out.println("Course removed: " + courseCode);

        } catch (SQLException e) {
            System.err.println("Error removing course: " + e.getMessage());
        }
    }

    @Override
    public void updateCourse(Course course) {
        String sql = "UPDATE courses SET course_name = ?, instructor_id = ? WHERE course_code = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, course.getCourseName());

            if (course.getInstructor() != null) {
                pstmt.setString(2, course.getInstructor().getId());
            } else {
                pstmt.setNull(2, Types.VARCHAR);
            }

            pstmt.setString(3, course.getCourseCode());

            pstmt.executeUpdate();
            System.out.println("Course updated: " + course.getCourseCode());

        } catch (SQLException e) {
            System.err.println("Error updating course: " + e.getMessage());
        }
    }

    @Override
    public Course getCourseByCode(String courseCode) {
        String sql = "SELECT * FROM courses WHERE course_code = ?";
        Course course = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, courseCode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                course = new Course(rs.getString("course_code"), rs.getString("course_name"));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching course: " + e.getMessage());
        }
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Course course = new Course(rs.getString("course_code"), rs.getString("course_name"));
                courses.add(course);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching courses: " + e.getMessage());
        }
        return courses;
    }
}