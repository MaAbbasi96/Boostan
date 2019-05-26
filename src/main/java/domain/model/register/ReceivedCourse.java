package domain.model.register;

import domain.model.course.Course;
import domain.model.course.CourseOffering;
import domain.model.register.exception.*;
import shared.ValueObject;

import java.sql.Time;

public class ReceivedCourse implements ValueObject<ReceivedCourse> {
    enum CourseState {REJECTED, PASSED, TAKEN, DELETED}
    private CourseOffering courseOffering;
    private CourseState state;
    private float score;

    public ReceivedCourse(CourseOffering course) {
        this.courseOffering = course;
        this.state = CourseState.TAKEN;
    }

    public float getScore() throws NotGradedCourseException {
        if (this.state.equals(CourseState.DELETED))
            throw new DeletedCourseException();
        else if (this.state.equals(CourseState.TAKEN))
            throw new TakenCourseException();
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public boolean sameValueAs(ReceivedCourse other) {
        return this.courseOffering.sameIdentityAs(other.courseOffering) && this.state.equals(other.state);
    }

    public boolean isPassed() {
        return this.state.equals(CourseState.PASSED);
    }

    public boolean isTaken() {
        return this.state.equals(CourseState.TAKEN);
    }

    public Course getCourse() {
        return this.courseOffering.getCourse();
    }

    public float getTotalNumberOfUnits() {
        return this.courseOffering.getTotalNumberOfUnits();
    }

    public CourseOffering getCourseOffering() {
        return this.courseOffering;
    }

    public void validateTimeConflict(CourseOffering courseOffering)
            throws ConflictClassTimeException {
        Time classStartTime = this.courseOffering.getClassStartTimeSlot();
        Time classEndTime = this.courseOffering.getClassEndTimeSlot();
        Time newClassStartTime = courseOffering.getClassStartTimeSlot();
        Time newClassEndTime = courseOffering.getClassEndTimeSlot();

        if ((newClassStartTime.after(classStartTime) && newClassStartTime.before(classEndTime)) ||
            (newClassEndTime.after(classStartTime) && newClassEndTime.before(classEndTime)) ||
            (classStartTime.after(newClassStartTime) && classStartTime.before(newClassEndTime)))
            throw new ConflictClassTimeException();
    }

    public void validateDuplicateOfferingCourse(CourseOffering courseOffering)
            throws DuplicateOfferingCourseException {
        if (this.courseOffering.sameIdentityAs(courseOffering))
            throw new DuplicateOfferingCourseException();
    }
}
