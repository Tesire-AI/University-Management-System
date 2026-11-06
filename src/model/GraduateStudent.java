package model;

public class GraduateStudent extends Student {
    private double[] grades;

    public GraduateStudent(String id, String firstname, String lastname, String email,String type, double[] grades) {
        super(id, firstname,lastname, email,type);
        this.grades = grades;
    }

    @Override
    public double calculateGPA() {
        double sum = 0;
        for (double g : grades) sum += g;
        double average = sum / grades.length;
        return average * 1.05;
    }
}