import DAO.*;

import model.Course;
import model.Enrollment;
import model.UndergraduateStudent;
import model.Student;

import java.util.Scanner;

public class MenuApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentDaoImpl studentDAO = new StudentDaoImpl();
        CourseDaoImpl courseDAO= new CourseDaoImpl();
        EnrollmentDaoImpl enrollmentDAO= new EnrollmentDaoImpl();


        while (true) {
            System.out.println("\n--- UNIVERSITY MANAGEMENT SYSTEM ---");
            System.out.println("1. Add a new Student");
            System.out.println("2. View all Students");
            System.out.println("3. Delete a Student");
            System.out.println("4. Add a new Course");
            System.out.println("5. View all Courses");
            System.out.println("6. Enroll a Student in a Course");
            System.out.println("7. View all Enrollments");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter First Name: ");
                    String fname = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lname = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();


                    Student newStudent = new UndergraduateStudent(id, fname, lname, email, "Undergraduate", new double[]{});
                    studentDAO.addStudent(newStudent, "Undergraduate");
                    break;
                case 2:
                    System.out.println("\n--- List of Students ---");
                    for (Student s : studentDAO.getAllStudents()) {
                        System.out.println(s.getid() + " -- " + s.getfirstName() + " " + s.getlastName() + " -- " + s.getemail());
                    }
                    break;
                case 3:
                    System.out.print("Enter Student ID to delete: ");
                    String deleteId = scanner.nextLine();
                    studentDAO.removeStudent(deleteId);
                    break;

                case 4:
                    System.out.print("Enter Course Code (e.g., CS101): ");
                    String courseCode = scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String courseName = scanner.nextLine();

                    Course newCourse = new Course(courseCode, courseName);
                    courseDAO.addCourse(newCourse);
                    break;

                case 5:
                    System.out.println("\n--- List of Courses ---");
                    for (Course c : courseDAO.getAllCourses()) {
                        System.out.println(c.getCourseCode() + " | " + c.getCourseName());
                    }
                    break;

                case 6:
                    System.out.print("Enter Student ID: ");
                    String enrollStudentId = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String enrollCourseCode = scanner.nextLine();


                    Student studentToEnroll = studentDAO.getStudentById(enrollStudentId);
                    Course courseToEnroll = courseDAO.getCourseByCode(enrollCourseCode);

                    if (studentToEnroll != null && courseToEnroll != null) {

                        Enrollment newEnrollment = new Enrollment(studentToEnroll, courseToEnroll, "2023-11-01");
                        enrollmentDAO.addEnrollment(newEnrollment);
                    } else {
                        System.out.println("Error: Student or Course not found in the database!");
                    }
                    break;

                case 7:
                    System.out.println("\n--- List of Enrollments ---");
                    for (Enrollment e : enrollmentDAO.getAllEnrollments()) {

                        System.out.println("Student ID: " + e.getStudent().getid() +
                                " enrolled in Course: " + e.getCourse().getCourseCode() +
                                " on " + e.getEnrollmentDate());
                    }
                    break;
                    case 8:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}