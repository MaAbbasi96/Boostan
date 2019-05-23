package domain.service;

import domain.model.course.Course;
import domain.model.course.CourseOffering;
import domain.model.register.Student;

import java.util.ArrayList;

public class CourseManagerService {
    private static CourseManagerService ourInstance = new CourseManagerService();
    public CourseManagerService getInstance(){
        return ourInstance;
    }

    public void takeCourse(Student student, CourseOffering courseOffering){
        ArrayList<Course> receiveCourses = student.getPassedCourses();
        ArrayList<Course> currentCourses = student.getCurrentCourses();


    }
}
