package domain.model.register;

import domain.model.common.Term;
import domain.model.course.Course;
import domain.model.course.CourseOffering;
import domain.model.register.exception.*;
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

    public void deleteCourse(CourseOffering courseOffering) throws NotDeleteStudentCourseException {
        if (getCurrentNumberOfUnits() < 12)
            throw new NumberOfUnitsBelowMinimumExceptionStudent();
        this.receivedCourses.remove(findReceivedCourse(courseOffering));
    }

    private int findReceivedCourse(CourseOffering courseOffering)
            throws CourseNotTakenException {
        for (int i = 0; i < this.receivedCourses.size(); i++)
            if (this.receivedCourses.get(i).getCourseOffering().sameIdentityAs(courseOffering))
                return i;
        throw new CourseNotTakenException();
    }

    private float getCurrentNumberOfUnits() {
        float numberOfUnit = 0;
        for (ReceivedCourse receivedCourse: this.receivedCourses)
            numberOfUnit += receivedCourse.getTotalNumberOfUnits();
        return numberOfUnit;
    }

    public void validateConditions(CourseOffering courseOffering)
            throws ConflictClassTimeException, DuplicateOfferingCourseException {
        validateGpa(courseOffering);
        validateTimeConflict(courseOffering);
        validateDuplicateCourse(courseOffering);
        validateCourseOfferingCapacity(courseOffering);
        validateIntershipConflict(courseOffering);
    }

    private void validateDuplicateCourse(CourseOffering courseOffering)
            throws DuplicateOfferingCourseException {
        for (ReceivedCourse receivedCourse: this.receivedCourses)
            receivedCourse.validateDuplicateOfferingCourse(courseOffering);
    }

    private void validateTimeConflict(CourseOffering courseOffering)
            throws ConflictClassTimeException {
        for (ReceivedCourse receivedCourse: this.receivedCourses)
            receivedCourse.validateTimeConflict(courseOffering);
    }
}
