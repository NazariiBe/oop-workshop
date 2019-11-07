package checkout.offers.conditions;

import checkout.Check;

public class ProductIdCondition implements Condition {
    private int id;

    public ProductIdCondition(int id) {
        this.id = id;
    }

    @Override
    public boolean isAllowed(Check check) {
        return check.getAmountByid(id) > 0;
    }
}
