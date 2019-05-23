package domain.model.register;

import domain.model.course.Course;
import domain.model.course.CourseOffering;
import domain.model.register.exception.DeletedCourseException;
import domain.model.register.exception.NotGradedCourseException;
import domain.model.register.exception.TakenCourseException;
import shared.ValueObject;

public class ReceivedCourse implements ValueObject<ReceivedCourse> {
    enum CourseState {REJECTED, PASSED, TAKEN, DELETED}
    private CourseOffering courseOffering;
    private CourseState state;
    private float score;

    public ReceivedCourse(CourseOffering course, CourseState state) {
        this.courseOffering = course;
        this.state = state;
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
}
