package model;

import java.util.Map;

public class UndergraduateStudent extends Student{


    private int credits;

    public UndergraduateStudent(String ID, String firstName, String lastName, String email) {
        super(ID, firstName, lastName, email);
    }
    private static final int requiredCredits = 120;
    private static final double minPassMark = 60;

    @Override
    public double calculateGPA() {
        double totalGPA=0;
        int totalCredits=0;

        for(Map.Entry<Course,Double> entry: getCompletedCourses().entrySet()){
            Course course= entry.getKey();
            Double marks= entry.getValue();

            totalGPA += marksToGradePoints(marks)* course.getCredits();
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

    @Override
    public String toString() {
        return "UndergraduateStudent{" +
                super.toString()+
                "credits=" + credits +
                '}';
    }
}
