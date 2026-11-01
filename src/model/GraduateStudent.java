package model;

import java.util.Map;

public class GraduateStudent extends Student{
    public GraduateStudent(String ID, String firstName, String lastName, String email) {
        super(ID, firstName, lastName, email);
    }

    private static final int requiredCredits = 36;
    private static final double minPassMark = 70;

    @Override
    public double calculateGPA() {
        double totalGPA=0;
        int totalCredits=0;

        for(Map.Entry<Course,Double> entry: getCompletedCourses().entrySet()){
            Course course= entry.getKey();
            Double marks= entry.getValue();
            double points= (marks<minPassMark)?0.0 : marksToGradePoints(marks);

            totalGPA += points* course.getCredits();
            totalCredits += course.getCredits();
        }

        if(totalCredits==0){
            return 0.0;
        }
        return totalGPA/totalCredits;
    }

    @Override
    public boolean isEligibleToGraduate() {
        return getTotalCreditsEarned() >= requiredCredits;
    }



}
