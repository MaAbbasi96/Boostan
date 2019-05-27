package domain.model.register;

import domain.model.common.Term;
import domain.model.course.Course;
import domain.model.course.CourseOffering;
import domain.model.course.exception.ClassCapacityFullException;
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

    public void validateConditions(CourseOffering courseOffering, float lastTermGpa)
            throws ConflictTimeException, DuplicateOfferingCourseException,
            MaximumNumberOfUnitsException, ClassCapacityFullException,
            InternshipTakenWithOtherCoursesException {
        validateGpa(courseOffering, lastTermGpa);
        validateClassTimeConflict(courseOffering);
        validateExamTimeConflict(courseOffering);
        validateDuplicateCourse(courseOffering);
        validateCourseOfferingCapacity(courseOffering);
        validateInternshipConflict(courseOffering);
    }

    private void validateInternshipConflict(CourseOffering courseOffering)
            throws InternshipTakenWithOtherCoursesException {
        if (this.getNumOfUnits() > 0 && courseOffering.getCourseName().equals("internship")
            || this.receivedCourses.get(0).getCourseName().equals("internship"))
            throw new InternshipTakenWithOtherCoursesException();
    }

    private void validateCourseOfferingCapacity(CourseOffering courseOffering)
            throws ClassCapacityFullException {
        courseOffering.validateCourseOfferingCapacity();
    }

    private void validateGpa(CourseOffering courseOffering, float lastTermGpa)
            throws MaximumNumberOfUnitsException {
        if (lastTermGpa < 12 && this.getNumOfUnits()+courseOffering.getTotalNumberOfUnits() > 14)
            throw new MaximumNumberOfUnitsDropoedStudentException();
        else if (lastTermGpa < 17 &&
                this.getNumOfUnits()+courseOffering.getTotalNumberOfUnits() > 20)
            throw new MaximumNumberOfUnitsUsualStudentException();
        else if (lastTermGpa >= 17 &&
                this.getNumOfUnits()+courseOffering.getTotalNumberOfUnits() > 24)
            throw new MaximumNumberOfUnitsTopStudentException();
    }

    private void validateDuplicateCourse(CourseOffering courseOffering)
            throws DuplicateOfferingCourseException {
        for (ReceivedCourse receivedCourse: this.receivedCourses)
            receivedCourse.validateDuplicateOfferingCourse(courseOffering);
    }

    private void validateClassTimeConflict(CourseOffering courseOffering)
            throws ConflictClassTimeException {
        for (ReceivedCourse receivedCourse: this.receivedCourses)
            receivedCourse.validateClassTimeConflict(courseOffering);
    }

    private void validateExamTimeConflict(CourseOffering courseOffering)
            throws ConflictExamTimeException {
        for (ReceivedCourse receivedCourse: this.receivedCourses)
            receivedCourse.validateExamTimeConflict(courseOffering);
    }

    private float getNumOfUnits() {
        float numOfUnits = 0;
        for (ReceivedCourse receivedCourse: this.receivedCourses)
            numOfUnits += receivedCourse.getTotalNumberOfUnits();
        return numOfUnits;
    }

    private float getSumOfScores() {
        float sumOfScores = 0;
        for (ReceivedCourse receivedCourse: this.receivedCourses) {
            try {
                sumOfScores += receivedCourse.getScore();
            } catch (NotGradedCourseException e) {
                // todo
            }
        }
        return sumOfScores;
    }

    public float getGpa() {
        return this.getSumOfScores() / this.getNumOfUnits();
    }

}
