package model;

public class Main {
    public static void main(String[] args) {


        UniversityManager manager= new UniversityManager();

        Course c1= new Course("C101","Computer Maintenance");
        Course c2= new Course("C102","Computer Networks");
        Course c3= new Course("S983","Software Security");
        Course c4= new Course("MATH8111","Applied Mathematics");
        Course c5= new Course("MATH1165","Descriptive Statistics");

        manager.addCourse(c1);
        manager.addCourse(c2);
        manager.addCourse(c3);
        manager.addCourse(c4);
        manager.addCourse(c5);

        Instructor i1= new Instructor("IT203","Joshua","Kabera","joshua12@gmail.com","IT");
        Instructor i2= new Instructor("MA204","Jean Paul","Muneza","jeanpaul34@gmail.com","Math");
        Instructor i3= new Instructor("MA205","Fidele","Hategekimana","Fidele67@gmail.com","Math");

        manager.addInstructor(i1);
        manager.addInstructor(i2);
        manager.addInstructor(i3);

        c1.setInstructor(i1);
        c2.setInstructor(i1);
        c3.setInstructor(i1);
        c4.setInstructor(i2);
        c5.setInstructor(i3);

        double[]m1={85.0, 90.0,72.5,68,95};
        double[]m2={75.0, 80.0,62.5,58,85};
        double[]m3={65.0, 70.0,52.5,48,75};
        double[]m4={55.0, 60.0,42.5,38,65};
        double[]m5={45.0, 70.0,32.5,28,55};

        Student s1= new GraduateStudent("SEN445","Husna","Umutoni","husna78@gmail.com","Graduate student",m1);
        Student s2= new GraduateStudent("NET564","Keza","Ornella","keza23@gmail.com","Graduate student",m2);
        Student s3= new UndergraduateStudent("SEN789","Daisy","Ikirezi","daisy67@gmail.com","Undergraduate student",m3);
        Student s4= new UndergraduateStudent("IMA461","Frank","Kamana","frank70@gmail.com","Undergraduate student",m4);
        Student s5= new UndergraduateStudent("NET602","David","Kaneza","david93@gmail.com","Undergraduate student",m5);

        manager.addStudent(s1);
        manager.addStudent(s2);
        manager.addStudent(s3);
        manager.addStudent(s4);
        manager.addStudent(s5);

        manager.enrollStudent("SEN445","C101");
        manager.enrollStudent("NET564","C102");
        manager.enrollStudent("SEN789","C101");
        manager.enrollStudent("IMA461","C102");
        manager.enrollStudent("NET602","S983");
        manager.enrollStudent("SEN445","MATH8111");
        manager.enrollStudent("NET564","MATH1165");




    }
}