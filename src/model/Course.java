package model;

import java.util.Queue;
import java.util.Set;

public class Course {

    private String courseCode;
    private String title;
    private int credits;
    private int maxCapacity;
    Instructor instructor;

    public Course(String courseCode, String title, int credits, int maxCapacity, Instructor instructor) {

        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
        this.maxCapacity = maxCapacity;
        this.instructor = instructor;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<Student> getRoster() {
        return roster;
    }

    public void setRoster(Set<Student> roster) {
        this.roster = roster;
    }

    public Queue<Student> getWaitList() {
        return waitList;
    }

    public void setWaitList(Queue<Student> waitList) {
        this.waitList = waitList;
    }

    Set<Student> roster;
    Queue<Student> waitList;










}
