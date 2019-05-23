package domain.model.course;

import domain.model.common.Term;
import domain.model.course.prerequisite.exception.PrerequisiteNotSatisfiedException;
import domain.model.register.Student;
import shared.Entity;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;

public class CourseOffering implements Entity<CourseOffering> {
    private int classNumber;
    private Teacher teacher;
    private TimeSlot classTimeSlot;
    private TimeSlot examTimeSlot;
    private Date examDate;
    private ArrayList<DayOfWeek> weekdays;
    private int capacity;
    private ArrayList<Student> attendees;
    private Term term;
    private Course course;

    public CourseOffering(int classNumber, Teacher teacher, TimeSlot classTimeSlot,
                          TimeSlot examTimeSlot, Date examDate, ArrayList<DayOfWeek> weekdays,
                          int capacity, Term term, Course course) {
        this.classNumber = classNumber;
        this.teacher = teacher;
        this.classTimeSlot = classTimeSlot;
        this.examTimeSlot = examTimeSlot;
        this.examDate = examDate;
        this.weekdays = weekdays;
        this.capacity = capacity;
        this.term = term;
        this.course = course;
        this.attendees = new ArrayList<>();
    }

    public boolean sameIdentityAs(CourseOffering other) {
        return this.classNumber == other.classNumber &&
                this.teacher.sameIdentityAs(other.teacher) &&
                this.classTimeSlot.sameValueAs(other.classTimeSlot) &&
                this.examTimeSlot.sameValueAs(other.examTimeSlot) &&
                this.examDate.equals(other.examDate) &&
                this.weekdays.equals(other.weekdays) &&
                this.capacity == other.capacity &&
                this.term.sameValueAs(other.term) &&
                this.course.sameValueAs(other.course);
    }

    public Course getCourse() {
        return this.course;
    }

    public void validatePrerequisites(ArrayList<Course> currentCourses,
                                      ArrayList<Course> passedCourses)
            throws PrerequisiteNotSatisfiedException {
        this.course.validatePrerequisites(currentCourses, passedCourses);
    }

    public void addAttendee(Student student) {
        this.attendees.add(student);
    }
}
