import dao.*;
import model.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("===== UNIVERSITY MANAGEMENT =====");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Delete student");
            System.out.println("4. Add course");
            System.out.println("5. View all courses");
            System.out.println("6. Delete course");
            System.out.println("7. Enroll student in course");
            System.out.println("8. View students with their courses");
            System.out.println("9. Update a grade");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            try {
                if (choice.equals("1")) {
                    System.out.print("Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("First name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Type (1 = Undergraduate, 2 = Graduate): ");
                    String type = scanner.nextLine();

                    Student student;
                    if (type.equals("2")) {
                        student = new GraduateStudent(id, firstName, lastName, email);
                    } else {
                        student = new UndergraduateStudent(id, firstName, lastName, email);
                    }
                    studentDAO.insert(student);
                    System.out.println("Student added.");

                } else if (choice.equals("2")) {
                    List<Student> students = studentDAO.findAll();
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    }
                    for (Student s : students) {
                        System.out.println(s.getid() + " - " + s.getemail());
                    }

                } else if (choice.equals("3")) {
                    System.out.print("Student ID to delete: ");
                    String id = scanner.nextLine();
                    studentDAO.delete(id);
                    System.out.println("Done (if the student existed, it was deleted).");

                } else if (choice.equals("4")) {
                    System.out.print("Course code: ");
                    String code = scanner.nextLine();
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Credits: ");
                    int credits = Integer.parseInt(scanner.nextLine());
                    System.out.print("Capacity: ");
                    int capacity = Integer.parseInt(scanner.nextLine());

                    Course course = new Course(code, title, credits, capacity, null);
                    courseDAO.insert(course);
                    System.out.println("Course added.");

                } else if (choice.equals("5")) {
                    List<Course> courses = courseDAO.findAll();
                    if (courses.isEmpty()) {
                        System.out.println("No courses found.");
                    }
                    for (Course c : courses) {
                        System.out.println(c.getCourseCode() + " - " + c.getTitle()
                                + " (" + c.getCredits() + " credits)");
                    }

                } else if (choice.equals("6")) {
                    System.out.print("Course code to delete: ");
                    String code = scanner.nextLine();
                    courseDAO.delete(code);
                    System.out.println("Done (if the course existed, it was deleted).");

                } else if (choice.equals("7")) {
                    System.out.print("Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Course code: ");
                    String courseCode = scanner.nextLine();
                    enrollmentDAO.enroll(studentId, courseCode);
                    System.out.println("Student enrolled.");

                } else if (choice.equals("8")) {
                    List<String> rows = enrollmentDAO.findStudentsWithCourses();
                    if (rows.isEmpty()) {
                        System.out.println("No enrollments found.");
                    }
                    for (String row : rows) {
                        System.out.println(row);
                    }

                } else if (choice.equals("9")) {
                    System.out.print("Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Course code: ");
                    String courseCode = scanner.nextLine();
                    System.out.print("Grade (0 - 100): ");
                    double grade = Double.parseDouble(scanner.nextLine());

                    if (grade < 0 || grade > 100) {
                        System.out.println("Grade must be between 0 and 100.");
                    } else {
                        enrollmentDAO.updateGrade(studentId, courseCode, grade);
                        System.out.println("Grade updated.");
                    }

                } else if (choice.equals("0")) {
                    System.out.println("Goodbye!");
                    running = false;

                } else {
                    System.out.println("Invalid option. Please choose from the menu.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}