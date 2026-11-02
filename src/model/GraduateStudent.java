package model;

import java.util.Map;

public class GraduateStudent extends Student {

    // graduate rule: any mark below 70 counts as 0 grade points
    private static final double MIN_PASS_MARK = 70;

    public GraduateStudent(String ID, String firstName, String lastName, String email) {
        super(ID, firstName, lastName, email);
    }

    @Override
    public double calculateGPA() {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Map.Entry<Course, Double> entry : getCompletedCourses().entrySet()) {
            Course course = entry.getKey();
            double marks = entry.getValue();

            double points;
            if (marks < MIN_PASS_MARK) {
                points = 0.0;
            } else {
                points = marksToGradePoints(marks);
            }

            totalPoints += points * course.getCredits();
            totalCredits += course.getCredits();
        }

        if (totalCredits == 0) {
            return 0.0;
        }
        return totalPoints / totalCredits;
    }



    @Override
    public String toString() {
        return "GraduateStudent{" + super.toString() + '}';
    }
}