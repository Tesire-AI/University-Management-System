package dao;

import model.Course;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // add a course to the database
    public void insert(Course course) throws SQLException {
        String sql = "INSERT INTO courses (code, title, credits, capacity) VALUES (?, ?, ?, ?)";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, course.getCourseCode());
        ps.setString(2, course.getTitle());
        ps.setInt(3, course.getCredits());
        ps.setInt(4, course.getMaxCapacity());

        ps.executeUpdate();
        con.close();
    }

    // get all courses
    public List<Course> findAll() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String code = rs.getString("code");
            String title = rs.getString("title");
            int credits = rs.getInt("credits");
            int capacity = rs.getInt("capacity");

            // instructor is not in the database, so it is null for now
            courses.add(new Course(code, title, credits, capacity, null));
        }

        con.close();
        return courses;
    }

    // delete a course by code
    public void delete(String code) throws SQLException {
        String sql = "DELETE FROM courses WHERE code = ?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, code);
        ps.executeUpdate();
        con.close();
    }
}