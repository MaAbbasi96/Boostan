package domain.model.register;

import domain.model.common.Person;

public class Student extends Person {
    public boolean sameIdentityAs(Person other) {
        return false;
    }
}
