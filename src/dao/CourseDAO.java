package dao;

import model.Course;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // add a course to the database
    public void insert(Course course) throws SQLException {
        String sql = "INSERT INTO courses (Coursecode, title, credits, capacity) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, course.getCourseCode());
            ps.setString(2, course.getTitle());
            ps.setInt(3, course.getCredits());
            ps.setInt(4, course.getMaxCapacity());

            ps.executeUpdate();
        }
    }

    // get all courses
    public List<Course> findAll() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Changed "code" to "Coursecode" to match the database column name in insert/delete
                String Coursecode = rs.getString("Coursecode");
                String title = rs.getString("title");
                int credits = rs.getInt("credits");
                int capacity = rs.getInt("capacity");

                // instructor is not in the database, so it is null for now
                courses.add(new Course(Coursecode, title, credits, capacity, null));
            }
        }
        return courses;
    }

    // delete a course by code
    public void delete(String Coursecode) throws SQLException {
        String sql = "DELETE FROM courses WHERE Coursecode = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, Coursecode);
            ps.executeUpdate();
        }
    }
}
