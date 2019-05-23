package domain.model.common;

import shared.ValueObject;

public class DatePeriod implements ValueObject<DatePeriod> {
    public boolean sameValueAs(DatePeriod other) {
        return false;
    }
}
