package checkout.offers.discounts;

import checkout.Check;

public class FixedDiscount implements Discount {
    private int discount;

    public FixedDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public int get(Check check) {
        return discount;
    }
}
