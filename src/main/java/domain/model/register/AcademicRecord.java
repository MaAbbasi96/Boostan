package domain.model.register;

import domain.model.common.Term;
import domain.model.course.Course;
import domain.model.offer.CourseOffering;
import domain.model.course.exception.ClassCapacityFullException;
import domain.model.register.exception.*;
import domain.model.register.exception.courseTakingException.*;
import shared.Entity;

import java.util.ArrayList;

public class AcademicRecord implements Entity<AcademicRecord> {
    private ArrayList<EnrolledCourse> enrolledCours;
    private Term term;

    public AcademicRecord(Term term) {
        this.enrolledCours = new ArrayList<>();
        this.term = term;
    }

    public boolean sameIdentityAs(AcademicRecord other) {
        return false; // todo: implement if needed
    }

    public ArrayList<Course> getPassedAndTakenCourses() {
        ArrayList<Course> passedReceivedCourses = new ArrayList<>();
        for(EnrolledCourse enrolledCourse : this.enrolledCours)
            if(enrolledCourse.isPassed() || enrolledCourse.isTaken())
                passedReceivedCourses.add(enrolledCourse.getCourse());
        return passedReceivedCourses;
    }

    public void receiveCourse(CourseOffering courseOffering) {
        enrolledCours.add(new EnrolledCourse(courseOffering));
    }

    public void deleteCourse(CourseOffering courseOffering) throws NotDeleteStudentCourseException {
        if (getCurrentNumberOfUnits() < 12)
            throw new NumberOfUnitsBelowMinimumException();
        this.enrolledCours.remove(findReceivedCourse(courseOffering));
    }

    private int findReceivedCourse(CourseOffering courseOffering)
            throws CourseNotTakenException {
        for (int i = 0; i < this.enrolledCours.size(); i++)
            if (this.enrolledCours.get(i).getCourseOffering().sameIdentityAs(courseOffering))
                return i;
        throw new CourseNotTakenException();
    }

    private float getCurrentNumberOfUnits() {
        float numberOfUnit = 0;
        for (EnrolledCourse enrolledCourse : this.enrolledCours)
            numberOfUnit += enrolledCourse.getTotalNumberOfUnits();
        return numberOfUnit;
    }

    public void validateConditions(CourseOffering courseOffering, float lastTermGpa)
            throws ConflictTimeException, DuplicateOfferingCourseTakenException,
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
            || this.enrolledCours.get(0).getCourseName().equals("internship"))
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
            throws DuplicateOfferingCourseTakenException {
        for (EnrolledCourse enrolledCourse : this.enrolledCours)
            enrolledCourse.validateDuplicateOfferingCourse(courseOffering);
    }

    private void validateClassTimeConflict(CourseOffering courseOffering)
            throws ConflictClassTimeException {
        for (EnrolledCourse enrolledCourse : this.enrolledCours)
            enrolledCourse.validateClassTimeConflict(courseOffering);
    }

    private void validateExamTimeConflict(CourseOffering courseOffering)
            throws ConflictExamTimeException {
        for (EnrolledCourse enrolledCourse : this.enrolledCours)
            enrolledCourse.validateExamTimeConflict(courseOffering);
    }

    private float getNumOfUnits() {
        float numOfUnits = 0;
        for (EnrolledCourse enrolledCourse : this.enrolledCours)
            numOfUnits += enrolledCourse.getTotalNumberOfUnits();
        return numOfUnits;
    }

    private float getSumOfScores() {
        float sumOfScores = 0;
        for (EnrolledCourse enrolledCourse : this.enrolledCours) {
            try {
                sumOfScores += enrolledCourse.getScore();
            } catch (NotGradedCourseException e) {
                System.out.println("This course not graded.");
            }
        }
        return sumOfScores;
    }

    public float getGpa() {
        return this.getSumOfScores() / this.getNumOfUnits();
    }

    public Term getTerm() {
        return term;
    }
}
