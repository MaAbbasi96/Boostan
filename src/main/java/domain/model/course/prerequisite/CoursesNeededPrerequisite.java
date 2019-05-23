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
    public void validate(ArrayList<Course> neededCourses) throws PrerequisiteNotSatisfiedException {
        for(Course neededCourse: neededCourses)
            if(!this.courses.contains(neededCourse))
                throw new CoursesNeededPrerequisiteNotSatisfiedException();
    }
}
