package model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Course {

    private String courseCode;
    private String title;
    private int credits;
    private int maxCapacity;
    private Instructor instructor;

    private Set<Student> roster;

    public Course(String courseCode, String title, int credits, int maxCapacity, Instructor instructor) {
        // the setters run the validation
        setCourseCode(courseCode);
        setTitle(title);
        setCredits(credits);
        setMaxCapacity(maxCapacity);
        this.instructor = instructor;   // can be null, assigned later
        this.roster = new LinkedHashSet<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        if (courseCode == null || courseCode.isBlank()) {
            throw new IllegalArgumentException("CourseCode cannot be empty");
        }
        this.courseCode = courseCode.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be empty");
        }
        this.title = title.trim();
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if (credits <= 0) {
            throw new IllegalArgumentException("Credits can't be less or equal to zero");
        }
        this.credits = credits;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("max capacity should not be less or equal to zero");
        }
        this.maxCapacity = maxCapacity;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        if (instructor == null) {
            throw new IllegalArgumentException("instructor can't be null");
        }
        this.instructor = instructor;
    }

    public Set<Student> getRoster() {
        return Collections.unmodifiableSet(roster);
    }

    public boolean isFull() {
        return roster.size() >= maxCapacity;
    }

    // no public/private: only classes in the model package (Enrollment) can change the roster
    void addToRoster(Student student) {
        roster.add(student);
    }

    void removeFromRoster(Student student) {
        roster.remove(student);
    }

    // two courses are the same if they have the same course code
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseCode.equals(course.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}