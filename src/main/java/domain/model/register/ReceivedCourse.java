package domain.model.register;

import domain.model.course.CourseOffering;
import domain.model.register.exception.DeletedCourseException;
import domain.model.register.exception.NotGradedCourseException;
import shared.ValueObject;

public class ReceivedCourse implements ValueObject<ReceivedCourse> {
    enum CourseState {REJECTED, PASSED, TAKEN, DELETED}
    private CourseOffering course;
    private CourseState state;
    private float score;

    public ReceivedCourse(CourseOffering course, CourseState state) {
        this.course = course;
        this.state = state;
    }

    public float getScore() throws Exception{
        if (this.state.equals(CourseState.DELETED))
            throw new DeletedCourseException();
        else if (this.state.equals(CourseState.TAKEN))
            throw new NotGradedCourseException();
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public boolean sameValueAs(ReceivedCourse other) {
        return this.course.sameIdentityAs(other.course) && this.state.equals(other.state);
    }
}
