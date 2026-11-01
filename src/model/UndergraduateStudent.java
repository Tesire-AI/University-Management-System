package model;

public class UndergraduateStudent extends Student{


    private int credits;

    public UndergraduateStudent(String ID, String firstName, String lastName, String email) {
        super(ID, firstName, lastName, email);
    }

    @Override
    public double calculateGPA() {
        return 0;
    }

    @Override
    public boolean isEligibleToGraduate() {
        return false;
    }

    @Override
    public void printDegreeProgress() {

    }
}
