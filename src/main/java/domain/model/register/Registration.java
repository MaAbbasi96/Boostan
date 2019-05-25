package domain.model.register;

import domain.model.common.Term;
import domain.model.course.Course;
import domain.model.course.CourseOffering;
import domain.model.register.exception.CanNotDeleteStudentCourseException;
import domain.model.register.exception.CourseNotTakenExceptionStudent;
import domain.model.register.exception.NumberOfUnitsBelowMinimumExceptionStudent;
import shared.Entity;

import java.util.ArrayList;

public class Registration implements Entity<Registration> {
    private ArrayList<ReceivedCourse> receivedCourses;
    private Term term;

    public Registration(Term term) {
        this.receivedCourses = new ArrayList<>();
        this.term = term;
    }

    public boolean sameIdentityAs(Registration other) {
        return false; // todo: implement if needed
    }

    public ArrayList<Course> getPassedAndTakenCourses() {
        ArrayList<Course> passedReceivedCourses = new ArrayList<>();
        for(ReceivedCourse receivedCourse: this.receivedCourses)
            if(receivedCourse.isPassed() || receivedCourse.isTaken())
                passedReceivedCourses.add(receivedCourse.getCourse());
        return passedReceivedCourses;
    }

    public void receiveCourse(CourseOffering courseOffering) {
        receivedCourses.add(new ReceivedCourse(courseOffering));
    }

    public void deleteCourse(CourseOffering courseOffering) throws CanNotDeleteStudentCourseException {
        float numberOfUnit = getCurrentNumberOfUnits();
        if (numberOfUnit < 12)
            throw new NumberOfUnitsBelowMinimumExceptionStudent();
        this.receivedCourses.remove(findReceivedCourse(courseOffering));
    }

    private ReceivedCourse findReceivedCourse(CourseOffering courseOffering)
            throws CourseNotTakenExceptionStudent {
        for (ReceivedCourse receivedCourse: this.receivedCourses)
            if (receivedCourse.getCourseOffering().sameIdentityAs(courseOffering))
                return receivedCourse;
        throw new CourseNotTakenExceptionStudent();
    }

    private float getCurrentNumberOfUnits() {
        float numberOfUnit = 0;
        for (ReceivedCourse receivedCourse: this.receivedCourses)
            numberOfUnit += receivedCourse.getTotalNumberOfUnits();
        return numberOfUnit;
    }
}
