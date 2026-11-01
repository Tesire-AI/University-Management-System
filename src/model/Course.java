package model;

import java.util.Queue;
import java.util.Set;

public class Course {

    private String courseCode;
    private String title;
    private int credits;
    private int maxCapacity;
    Instructor instructor;
    TimeSlot timeSlot;

    Set<Course> prerequisites;
    Set<Student> roster;
    Queue<Student> waitList;










}
