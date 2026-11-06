package DAO;

import model.Enrollment;
import java.util.List;

public interface EnrollmentDAO {
    void addEnrollment(Enrollment enrollment);

    void removeEnrollment(String studentId, String courseCode);

    Enrollment getEnrollment(String studentId, String courseCode);

    List<Enrollment> getAllEnrollments();
}