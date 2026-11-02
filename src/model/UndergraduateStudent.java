package model;

import java.util.Map;

public class UndergraduateStudent extends Student {

    public UndergraduateStudent(String ID, String firstName, String lastName, String email) {
        super(ID, firstName, lastName, email);
    }

    // undergraduate rule: every mark counts, with no minimum pass mark
    @Override
    public double calculateGPA() {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Map.Entry<Course, Double> entry : getCompletedCourses().entrySet()) {
            Course course = entry.getKey();
            double marks = entry.getValue();

            totalPoints += marksToGradePoints(marks) * course.getCredits();
            totalCredits += course.getCredits();
        }

        if (totalCredits == 0) {
            return 0.0;
        }
        return totalPoints / totalCredits;
    }

    @Override
    public String toString() {
        return "UndergraduateStudent{" + super.toString() + '}';
    }
}