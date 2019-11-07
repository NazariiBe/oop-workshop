package checkout.offers.conditions;

import checkout.Check;

public class MinimalCostCondition implements Condition {
    private int minimalCost;

    public MinimalCostCondition(int minimalCost) {
        this.minimalCost = minimalCost;
    }

    @Override
    public boolean isAllowed(Check check) {
        return this.minimalCost <= check.getTotalCost();
    }
}
