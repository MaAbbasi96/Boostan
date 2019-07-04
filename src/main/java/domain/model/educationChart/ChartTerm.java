package domain.model.educationChart;

import shared.ValueObject;

import java.util.ArrayList;

public class ChartTerm implements ValueObject<ChartTerm> {
    private ArrayList<ChartTermItem> chartTermItems;

    public ChartTerm(ArrayList<ChartTermItem> chartTermItems) {
        this.chartTermItems = chartTermItems;
    }

    @Override
    public boolean sameValueAs(ChartTerm other) {
        return false; //todo: implement if necessary
    }
}
