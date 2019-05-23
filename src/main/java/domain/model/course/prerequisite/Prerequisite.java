package domain.model.course.prerequisite;

import domain.model.course.Course;
import domain.model.course.prerequisite.exception.PrerequisiteNotSatisfiedException;

import java.util.ArrayList;

public interface Prerequisite {
    public void validate(ArrayList<Course> courses) throws PrerequisiteNotSatisfiedException;
}
