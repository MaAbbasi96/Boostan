package domain.model.educationChart;

import shared.ValueObject;

import java.util.ArrayList;

public class Chart implements ValueObject<Chart> {
    private int entranceYear;
    private ArrayList<ChartTermItem> chartTermItems;

    public Chart(int entranceYear, ArrayList<ChartTermItem> chartTermItems) {
        this.entranceYear = entranceYear;
        this.chartTermItems = chartTermItems;
    }

    @Override
    public boolean sameValueAs(Chart other) {
        return this.entranceYear == other.entranceYear &&
                this.chartTermItems.equals(other.chartTermItems);
    }
}
