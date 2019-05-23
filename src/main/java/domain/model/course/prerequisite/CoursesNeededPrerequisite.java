package domain.model.course.prerequisite;

import domain.model.course.Course;
import domain.model.course.prerequisite.exception.CoursesNeededPrerequisiteNotSatisfiedException;
import domain.model.course.prerequisite.exception.PrerequisiteNotSatisfiedException;

import java.util.ArrayList;

public class CoursesNeededPrerequisite extends CoursePrerequisite {

    public CoursesNeededPrerequisite(ArrayList<Course> courses) {
        super(courses);
    }

    @Override
    public void validate(ArrayList<Course> courses) throws PrerequisiteNotSatisfiedException {
        for(Course course: courses)
            if(!this.courses.contains(course))
                throw new CoursesNeededPrerequisiteNotSatisfiedException();
    }
}
