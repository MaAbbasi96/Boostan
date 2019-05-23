package domain.model.register;

import shared.Entity;

public class Registration implements Entity {
    public boolean sameIdentityAs(Object other) {
        return false;
    }
}
