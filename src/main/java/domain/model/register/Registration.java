package domain.model.register;

import domain.model.common.Term;
import domain.model.course.Course;
import shared.Entity;

import java.util.ArrayList;

public class Registration implements Entity<Registration> {
    private ArrayList<ReceivedCourse> receivedCourses;
    private Term term;

    public Registration(Term term) {
        this.receivedCourses = new ArrayList<>();
        this.term = term;
    }

    public boolean sameIdentityAs(Registration other) {
        return false; // todo: implement if needed
    }

    public ArrayList<Course> getPassedAndTakenCourses() {
        ArrayList<Course> passedReceivedCourses = new ArrayList<>();
        for(ReceivedCourse receivedCourse: this.receivedCourses)
            if(receivedCourse.isPassed() || receivedCourse.isTaken())
                passedReceivedCourses.add(receivedCourse.getCourse());
        return passedReceivedCourses;
    }
}
