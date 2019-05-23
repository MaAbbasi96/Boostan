package domain.model.course;

import domain.model.common.Person;

public class Teacher extends Person {
    public boolean sameIdentityAs(Person other) {
        return false;
    }
}
