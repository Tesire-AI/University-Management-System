package dao;

import model.*;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // add a student to the database
    public void insert(Student student) throws SQLException {
        String sql = "INSERT INTO students (id, first_name, last_name, email, type) VALUES (?, ?, ?, ?, ?)";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, student.getid());
        ps.setString(2, student.getfirstName());
        ps.setString(3, student.getlastName());
        ps.setString(4, student.getemail());

        // save the type so we know which class to create when we read it back
        if (student instanceof GraduateStudent) {
            ps.setString(5, "GRAD");
        } else {
            ps.setString(5, "UNDERGRAD");
        }

        ps.executeUpdate();
        con.close();
    }

    // get all students
    public List<Student> findAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String id = rs.getString("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String type = rs.getString("type");

            // make the right kind of student
            if (type.equals("GRAD")) {
                students.add(new GraduateStudent(id, firstName, lastName, email));
            } else {
                students.add(new UndergraduateStudent(id, firstName, lastName, email));
            }
        }

        con.close();
        return students;
    }

    // delete a student by id
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
        con.close();
    }
}