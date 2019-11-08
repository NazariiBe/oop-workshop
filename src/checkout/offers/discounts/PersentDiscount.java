package checkout.offers.discounts;

import checkout.Check;

public class PersentDiscount implements Discount {
    private float percent;

    public PersentDiscount(float percent) {
        this.percent = percent;
    }

    @Override
    public int get(Check check) {
        return (int) (check.getTotalCostWithDiscount() * percent);
    }
}
