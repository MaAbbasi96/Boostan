package domain.model.course;

import shared.ValueObject;

public class TimeSlot implements ValueObject<TimeSlot> {
    public boolean sameValueAs(TimeSlot other) {
        return false;
    }
}
