package domain.model.register;

import domain.model.course.CourseOffering;
import domain.model.register.state.CourseStateInterface;
import shared.ValueObject;

public class ReceivedCourse implements ValueObject<ReceivedCourse> {
    private CourseOffering course;
    private CourseStateInterface state;

    public ReceivedCourse(CourseOffering course, CourseStateInterface state) {
        this.course = course;
        this.state = state;
    }

    public boolean sameValueAs(ReceivedCourse other) {
        return this.course.sameIdentityAs(other.course) && this.state.equals(other.state);
    }
}
