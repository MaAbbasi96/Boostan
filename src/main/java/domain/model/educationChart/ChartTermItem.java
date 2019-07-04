package domain.model.educationChart;

import domain.model.course.Course;
import shared.Entity;

public class ChartTermItem implements Entity<ChartTermItem> {
    private Course course;

    public ChartTermItem(Course course) {
        this.course = course;
    }

    @Override
    public boolean sameIdentityAs(ChartTermItem other){
        return this.course.sameValueAs(other.course);
    }
}
