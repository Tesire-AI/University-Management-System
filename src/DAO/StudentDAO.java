package DAO;

import model.Student;
import java.util.List;

public interface StudentDAO {


    public void addStudent(Student student,String type);

    public void removeStudent(String id);

    public void updateStudent(Student student);

    public Student getStudentById(String id);

    public List<Student> getAllStudents();
}
