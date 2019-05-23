package domain.model.register;

import domain.model.common.Name;
import domain.model.common.Person;
import domain.model.common.Term;

import java.util.ArrayList;
import java.util.Date;

public class Student extends Person {
    private ArrayList<Registration> registrations;
    private Term firstTerm;

    public Student(Name name, String nationalCode, String id, Date birthDate, ArrayList<Registration> registrations,
                   Term firstTerm) {
        super(name, nationalCode, id, birthDate);
        this.registrations = registrations;
        this.firstTerm = firstTerm;
    }
}
