package checkout.offers.conditions;

import checkout.Category;
import checkout.Check;

public class MinimalCostByCategory implements Condition {
    private int cost;
    private Category category;

    public MinimalCostByCategory(Category category, int cost) {
        this.cost = cost;
        this.category = category;
    }

    @Override
    public boolean isAllowed(Check check) {
        return this.cost <= check.getCostByCategory(this.category);
    }
}
