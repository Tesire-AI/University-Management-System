package model;

public class Enrollment {

    private Student student;
    private Course course;
    private Double grade;   // null until the course is graded

    public Enrollment(Student student, Course course) {
        if (student == null) {
            throw new IllegalArgumentException("Student can't be empty");
        }
        if (course == null) {
            throw new IllegalArgumentException("Course can't be empty");
        }
        this.student = student;
        this.course = course;
        this.grade = null;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Double getGrade() {
        return grade;
    }

    public boolean isCompleted() {
        return grade != null;
    }

    // all enrollment rules:
    public void enroll() {
        if (student.getCompletedCourses().containsKey(course)) {
            throw new IllegalArgumentException("Student has already completed " + course.getCourseCode());
        }
        if (course.getRoster().contains(student)) {
            throw new IllegalArgumentException("Student is already enrolled in " + course.getCourseCode());
        }
        if (course.isFull()) {
            throw new IllegalStateException("Course " + course.getCourseCode() + " is full");
        }
        student.addEnrolledCourse(course);   // update the student's side
        course.addToRoster(student);         // update the course's side
    }

    public void drop() {
        if (!course.getRoster().contains(student)) {
            throw new IllegalStateException("Student is not enrolled in " + course.getCourseCode());
        }
        course.removeFromRoster(student);
        student.removeEnrolledCourse(course);
    }

    public void recordGrade(double marks) {
        if (!course.getRoster().contains(student)) {
            throw new IllegalStateException("Student is not enrolled in " + course.getCourseCode());
        }
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks should be between 0 and 100");
        }
        this.grade = marks;
        student.addCompletedCourses(course, marks);
    }

    @Override
    public String toString() {
        return "Enrollment{student=" + student.getemail() +
                ", course=" + course.getCourseCode() +
                ", grade=" + (grade == null ? "not graded yet" : grade) + '}';
    }
}