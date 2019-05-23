package domain.model.common;

import shared.ValueObject;

public class Term implements ValueObject<Term> {
    private DatePeriod period;
    private DatePeriod registrationPeriod;
    private String name;
    private static int termNumber = 1;

    public Term(DatePeriod period, DatePeriod registrationPeriod, String name) {
        this.period = period;
        this.registrationPeriod = registrationPeriod;
        this.name = name;
        termNumber++;
    }

    public boolean sameValueAs(Term other) {
        return false;
    }
}
