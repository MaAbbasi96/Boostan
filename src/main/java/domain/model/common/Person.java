package domain.model.common;

import domain.model.course.CourseOffering;
import shared.Entity;

import java.util.Date;

abstract public class Person implements Entity<Person> {
    private Name name;
    private String nationalCode;
    private String id;
    private Date birthDate;

    public Person(Name name, String nationalCode, String id, Date birthDate) {
        this.name = name;
        this.nationalCode = nationalCode;
        this.id = id;
        this.birthDate = birthDate;
    }

    public boolean sameIdentityAs(Person other) {
        return this.name.sameValueAs(other.name) && this.nationalCode.equals(other.nationalCode)
                && this.birthDate.equals(other.birthDate) && this.id.equals(other.id);
    }
}
