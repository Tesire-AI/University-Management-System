package dao;

import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    // enroll a student in a course
    public void enroll(String studentId, String courseCode) throws SQLException {
        String sql = "INSERT INTO enrollments (student_id, course_code) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, studentId);
            ps.setString(2, courseCode);
            ps.executeUpdate();
        }
    }

    // change the grade of an enrollment
    public void updateGrade(String studentId, String courseCode, double grade) throws SQLException {
        String sql = "UPDATE enrollments SET grade = ? WHERE student_id = ? AND course_code = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, grade);
            ps.setString(2, studentId);
            ps.setString(3, courseCode);
            ps.executeUpdate();
        }
    }

    // JOIN: show each student with the courses they are enrolled in
    public List<String> findStudentsWithCourses() throws SQLException {
        List<String> results = new ArrayList<>();

        String sql = "SELECT s.first_name, s.last_name, c.code, c.title, e.grade "
                + "FROM students s "
                + "JOIN enrollments e ON s.id = e.student_id "
                + "JOIN courses c ON c.code = e.course_code";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String grade = rs.getString("grade");   // null if the student has no grade yet
                if (grade == null) {
                    grade = "no grade yet";
                }

                String line = rs.getString("first_name") + " " + rs.getString("last_name")
                        + " - " + rs.getString("code") + " " + rs.getString("title")
                        + " - grade: " + grade;
                results.add(line);
            }
        }
        return results;
    }
}
