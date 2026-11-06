package DAO;

import DAO.StudentDAO;
import model.GraduateStudent;
import model.Student;
import model.UndergraduateStudent;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDAO {

    @Override
    public void addStudent(Student student, String type) {
        String sql = "INSERT INTO students (id, firstname, lastname, email, student_type) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getid());
            pstmt.setString(2, student.getfirstName());
            pstmt.setString(3, student.getlastName());
            pstmt.setString(4, student.getemail());
            pstmt.setString(5, type);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Success: " + student.getfirstName() + " was added to the database.");
            }

        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    @Override
    public void removeStudent(String id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Success: Student with ID " + id + " was removed.");
            } else {
                System.out.println("Notice: No student found with ID " + id + ".");
            }

        } catch (SQLException e) {
            System.err.println("Error removing student: " + e.getMessage());
        }
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET firstname = ?, lastname = ?, email = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getfirstName());
            pstmt.setString(2, student.getlastName());
            pstmt.setString(3, student.getemail());
            pstmt.setString(4, student.getid());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Success: Student with ID " + student.getid() + " was updated.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
        }
    }

    @Override
    public Student getStudentById(String id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        Student student = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                String studentId = rs.getString("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                String type = rs.getString("student_type");
                double[] emptyGrades = new double[]{};

                if (type != null && type.trim().equalsIgnoreCase("Graduate")) {
                    student = new GraduateStudent(studentId, firstName, lastName, email, type, emptyGrades);
                } else {
                    student = new UndergraduateStudent(studentId, firstName, lastName, email, type, emptyGrades);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching student: " + e.getMessage());
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                String studentId = rs.getString("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                String type = rs.getString("student_type");
                double[] emptyGrades = new double[]{};

                Student student;
                if (type != null && type.trim().equalsIgnoreCase("Graduate")) {
                    student = new GraduateStudent(studentId, firstName, lastName, email, type, emptyGrades);
                } else {
                    student = new UndergraduateStudent(studentId, firstName, lastName, email, type, emptyGrades);
                }

                students.add(student);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching all students: " + e.getMessage());
        }
        return students;
    }
}