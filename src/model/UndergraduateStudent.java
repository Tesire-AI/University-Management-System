package model;

public class UndergraduateStudent extends Student {
    private double[] grades;

    public UndergraduateStudent(String id, String firstname,String lastname, String email,String type, double[] grades) {
        super(id, firstname,lastname, email, type);
        this.grades = grades;
    }

    @Override
    public double calculateGPA() {

        double sum = 0;
        for (double g : grades) sum += g;
        return sum / grades.length;
    }
}