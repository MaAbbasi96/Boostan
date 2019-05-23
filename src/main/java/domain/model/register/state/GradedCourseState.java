package domain.model.register.state;

public class GradedCourseState implements CourseStateInterface {
    private int grade;

    public GradedCourseState(int grade) {
        this.grade = grade;
    }
}
