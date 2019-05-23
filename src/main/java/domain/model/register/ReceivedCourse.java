package domain.model.register;

import shared.ValueObject;

public class ReceivedCourse implements ValueObject<ReceivedCourse> {
    public boolean sameValueAs(ReceivedCourse other) {
        return false;
    }
}
