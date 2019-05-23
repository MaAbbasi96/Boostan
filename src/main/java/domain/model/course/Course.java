package domain.model.course;

import shared.ValueObject;

public class Course implements ValueObject<Course> {
    public boolean sameValueAs(Course other) {
        return false;
    }
}
