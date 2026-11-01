package model;

public class GraduateStudent extends Student{
    public GraduateStudent(String ID, String firstName, String lastName, String email) {
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
