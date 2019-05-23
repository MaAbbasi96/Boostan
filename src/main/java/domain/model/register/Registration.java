package domain.model.register;

import domain.model.common.Term;
import shared.Entity;

import java.util.ArrayList;

public class Registration implements Entity<Registration> {
    private ArrayList<ReceivedCourse> receivedCourses;
    private Term term;
    private Student student;

    public Registration(Term term, Student student) {
        this.student = student;
        this.receivedCourses = new ArrayList<>();
        this.term = term;
    }

    public boolean sameIdentityAs(Registration other) {
        return false;
    }
}
