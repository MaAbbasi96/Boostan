package domain.model.course.prerequisite;

import domain.model.course.Course;
import domain.model.course.prerequisite.exception.NumOfCoursesPassedPrerequisiteNotSatisfiedException;
import domain.model.course.prerequisite.exception.PrerequisiteNotSatisfiedException;
import java.util.ArrayList;


public class NumOfCoursesPassedPrerquisite implements Prerequisite {
    private float threshold;

    public NumOfCoursesPassedPrerquisite(float threshold) {
        this.threshold = threshold;
    }

    @Override
    public void validate(ArrayList<Course> courses) throws PrerequisiteNotSatisfiedException {
        if(this.threshold > this.countCoursesPassed(courses))
            throw new NumOfCoursesPassedPrerequisiteNotSatisfiedException();
    }

    private int countCoursesPassed(ArrayList<Course> courses){
        int count = 0;
        for(Course course: courses)
            count += course.getTotalNumOfUnits();
        return count;
    }
}
