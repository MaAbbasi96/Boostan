package domain.model.course.prerequisite;

import domain.model.course.Course;
import domain.model.course.prerequisite.exception.NumOfCoursesPassedPrerequisiteNotSatisfiedException;
import domain.model.course.prerequisite.exception.PrerequisiteNotSatisfiedException;
import java.util.ArrayList;


public class NumOfCoursesPassedPrerquisite implements Prerequisite {
    private int threshold;

    public NumOfCoursesPassedPrerquisite(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void validate(ArrayList<Course> courses) throws PrerequisiteNotSatisfiedException {
        int numOfUnits = countCoursesPassed(courses);
        if(this.threshold > numOfUnits)
            throw new NumOfCoursesPassedPrerequisiteNotSatisfiedException();
    }

    private int countCoursesPassed(ArrayList<Course> courses){
        int count = 0;
        for(Course course: courses)
            count += course.getSumOfNumsOfUnits();
        return count;
    }
}
