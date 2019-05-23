package domain.model.common;

import shared.ValueObject;

public class Name implements ValueObject<Name> {
    public boolean sameValueAs(Name other) {
        return false;
    }
}
