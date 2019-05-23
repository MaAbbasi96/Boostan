package domain.model.register;

import domain.model.common.Term;
import shared.Entity;

import java.util.ArrayList;

public class Registration implements Entity<Registration> {
    private ArrayList<ReceivedCourse> receivedCourses;
    private Term term;

    public Registration(ArrayList<ReceivedCourse> receivedCourses, Term term) {
        this.receivedCourses = new ArrayList<>();
        this.term = term;
    }

    public boolean sameIdentityAs(Registration other) {
        return this.receivedCourses.equals(other.receivedCourses) && this.term.equals(other.term);
    }
}
