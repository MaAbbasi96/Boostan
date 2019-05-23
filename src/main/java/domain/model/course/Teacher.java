package domain.model.course;

import domain.model.common.Name;
import domain.model.common.Person;

import java.util.Date;

public class Teacher extends Person {
    private int roomNumber;

    public Teacher(Name name, String nationalCode, String id, Date birthDate, int roomNumber) {
        super(name, nationalCode, id, birthDate);
        this.roomNumber = roomNumber;
    }
}
