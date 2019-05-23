package domain.model.course;

import shared.ValueObject;

public class NumOfUnits implements ValueObject<NumOfUnits> {
    private float theoretical;
    private float practical;

    public NumOfUnits(float theoretical, float practical) {
        this.theoretical = theoretical;
        this.practical = practical;
    }

    public boolean sameValueAs(NumOfUnits other) {
        return this.practical == other.practical && this.theoretical == other.theoretical;
    }
}
