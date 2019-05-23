package domain.model.course;

import domain.model.course.prerequisite.Prerequisite;
import shared.ValueObject;

import java.util.ArrayList;

public class Course implements ValueObject<Course> {
    private String name;
    private NumOfUnits units;
    private ArrayList<Prerequisite> prerequisites;

    public Course(String name, NumOfUnits units, ArrayList<Prerequisite> prerequisites) {
        this.name = name;
        this.units = units;
        this.prerequisites = prerequisites;
    }

    public boolean sameValueAs(Course other) {
        return this.name.equals(other.name) && this.units.sameValueAs(other.units);
    }

    public ArrayList<Prerequisite> getPrerequisites() {
        return prerequisites;
    }
    public int getTotalNumsOfUnits(){
        return (int) (this.units.getPractical() + this.units.getTheoretical());
    }
}
