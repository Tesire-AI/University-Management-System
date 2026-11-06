package DAO;

import DAO.EnrollmentDAO;
import model.Enrollment;
import model.Student;
import model.Course;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDaoImpl implements EnrollmentDAO {

    @Override
    public void addEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments (student_id, course_code, enrollment_date, grade) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setString(1, enrollment.getStudent().getid());
            pstmt.setString(2, enrollment.getCourse().getCourseCode());
            pstmt.setString(3, enrollment.getEnrollmentDate());
            pstmt.setDouble(4, enrollment.getGrade());

            pstmt.executeUpdate();
            System.out.println("Enrollment created: " + enrollment.getStudent().getfirstName()+ " in " + enrollment.getCourse().getCourseName());

        } catch (SQLException e) {
            System.err.println("Error creating enrollment: " + e.getMessage());
        }
    }

    @Override
    public void removeEnrollment(String studentId, String courseCode) {
        String sql = "DELETE FROM enrollments WHERE student_id = ? AND course_code = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);
            pstmt.setString(2, courseCode);

            pstmt.executeUpdate();
            System.out.println("Enrollment removed.");

        } catch (SQLException e) {
            System.err.println("Error removing enrollment: " + e.getMessage());
        }
    }

    @Override
    public Enrollment getEnrollment(String studentId, String courseCode) {
        String sql = "SELECT * FROM enrollments WHERE student_id = ? AND course_code = ?";
        Enrollment enrollment = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);
            pstmt.setString(2, courseCode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Student dummyStudent = new model.UndergraduateStudent(studentId, "Temp", "Temp", "temp@temp.com", "Undergraduate", new double[]{});
                Course dummyCourse = new Course(courseCode, "Temp Course");

                enrollment = new Enrollment(dummyStudent, dummyCourse, rs.getString("enrollment_date"));
                enrollment.setGrade(rs.getDouble("grade"));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching enrollment: " + e.getMessage());
        }
        return enrollment;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM enrollments";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                String studentId = rs.getString("student_id");
                String courseCode = rs.getString("course_code");

                Student dummyStudent = new model.UndergraduateStudent(studentId, "Temp", "Temp", "temp@temp.com", "Undergraduate", new double[]{});
                Course dummyCourse = new Course(courseCode, "Temp Course");

                Enrollment enrollment = new Enrollment(dummyStudent, dummyCourse, rs.getString("enrollment_date"));
                enrollment.setGrade(rs.getDouble("grade"));

                enrollments.add(enrollment);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching all enrollments: " + e.getMessage());
        }
        return enrollments;
    }
}