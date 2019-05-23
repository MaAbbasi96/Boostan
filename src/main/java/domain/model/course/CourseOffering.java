package domain.model.course;

import shared.Entity;

public class CourseOffering implements Entity<CourseOffering> {
    public boolean sameIdentityAs(CourseOffering other) {
        return false;
    }
}
