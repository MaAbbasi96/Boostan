package domain.model.course;

import domain.model.common.Name;
import domain.model.common.PersonalInfo;

import java.util.Date;

public class Teacher {
    private int roomNumber;
    private PersonalInfo personalInfo;

    public Teacher(Name name, String nationalCode, String id, Date birthDate, int roomNumber) {
        this.personalInfo = new PersonalInfo(name, nationalCode, id, birthDate);
        this.roomNumber = roomNumber;
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof Teacher))
            return false;
        return this.personalInfo.sameIdentityAs(((Teacher) other).personalInfo);
    }
}
