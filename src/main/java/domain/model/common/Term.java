package domain.model.common;

import shared.ValueObject;

public class Term implements ValueObject<Term> {
    public boolean sameValueAs(Term other) {
        return false;
    }
}
