package domain.model.register;

import domain.model.common.Name;
import domain.model.common.Person;
import domain.model.common.Term;
import domain.model.course.Course;
import domain.model.course.CourseOffering;
import domain.model.course.exception.ClassCapacityFullException;
import domain.model.register.exception.NotDeleteStudentCourseException;
import domain.model.register.exception.courseTakingException.CourseTakingException;

import java.util.ArrayList;
import java.util.Date;

public class Student extends Person {
    private ArrayList<Registration> finishedRegistrations;
    private Registration currentRegistration;
    private Term firstTerm;

    public Student(Name name, String nationalCode, String id, Date birthDate, Term firstTerm) {
        super(name, nationalCode, id, birthDate);
        this.finishedRegistrations = new ArrayList<>();
        this.firstTerm = firstTerm;
    }

    public ArrayList<Course> getPassedCourses(){
        ArrayList<Course> passedCourses = new ArrayList<>();
        for(Registration registration: this.finishedRegistrations)
            passedCourses.addAll(registration.getPassedAndTakenCourses());
        return passedCourses;
    }

    public ArrayList<Course> getCurrentCourses(){
        return new ArrayList<>(currentRegistration.getPassedAndTakenCourses());
    }

    public void receiveCourse(CourseOffering courseOffering) {
        this.currentRegistration.receiveCourse(courseOffering);
    }

    public void deleteCourse(CourseOffering courseOffering) throws NotDeleteStudentCourseException {
        this.currentRegistration.deleteCourse(courseOffering);
    }

    public void validateConditions(CourseOffering courseOffering) throws CourseTakingException,
            ClassCapacityFullException {
        this.currentRegistration.validateConditions(courseOffering, this.calculateLastTermGpa());
    }

    private float calculateLastTermGpa(){
        Registration lastTermRegistration =
                this.finishedRegistrations.get(this.finishedRegistrations.size()-1);
        return lastTermRegistration.getGpa();
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof Student))
            return false;
        return this.sameIdentityAs((Person) other);
    }


}
