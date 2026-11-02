import model.*;

import java.util.*;

public class Main {
    public static void main(String[] args){

        Instructor prof1 = new Instructor("I001","John","Smith","john@uni.com","Computer Science");
        Instructor prof2 = new Instructor("I002","Kate","Teta","Teta@uni.com","Computer Maintenance");



        Course c1= new Course("C101","Computer Science",3,3,prof1);
        Course c2= new Course("C102","Computer Maintenance",3,3,prof2);
        prof1.assignCourse(c1);
        prof2.assignCourse(c2);


        Student s1=new UndergraduateStudent("S001","Bana","Cephas","BanaCephas@gmail.com");
        Student s2=new GraduateStudent("S002","Ryan","Frank","Ryan@gmail.com");
        Student s3=new UndergraduateStudent("S003","Daisy","Gahima","Gahima@gmail.com");



        System.out.println("--Enrolling and grading--");
        Enrollment e1= new Enrollment(s1,c1);
        Enrollment e2= new Enrollment(s2,c1);
        Enrollment e3= new Enrollment(s3,c1);

        e1.enroll();
        e2.enroll();
        e3.enroll();
        e1.recordGrade(85);
        e2.recordGrade(55);
        e3.recordGrade(59);
        List<Student> students= new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);

        for( Student s: students){
            System.out.println(s.getfirstName()+" "+s.getlastName()+" "+"GPA ="+s.calculateGPA());



            System.out.println("\n=== Error cases ===");

            Enrollment full1 = new Enrollment(s1, c2);   // c2 has capacity 1
            full1.enroll();

            try {
                new Enrollment(s2, c2).enroll();       // course is full
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            try {
                new Enrollment(s1, c2).enroll();         // already enrolled
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            try {
                e1.recordGrade(150);                     // invalid marks
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // ---------- 4. collections ----------
            System.out.println("\n=== Collections ===");

            Map<String, Student> studentsById = new HashMap<>();   // lookup by ID
            for (Student st : students) {
                studentsById.put(st.getid(), s);
            }
            System.out.println("Lookup S002: " + studentsById.get("S002").getemail());

            System.out.println("Roster of CS101: " + c1.getRoster().size() + " students");
            System.out.println("Courses taught by instructor: "+" " + prof1.getCourses());

            // students sorted by GPA, highest first
            students.sort(Comparator.comparingDouble(Student::calculateGPA).reversed());
            System.out.println("\nRanking by GPA:");
            for (Student st : students) {
                System.out.println(st.getemail() + " -> " + st.calculateGPA());
            }

        }

    }
}
