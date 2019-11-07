package checkout.offers;

import checkout.Check;
import checkout.offers.conditions.Condition;
import checkout.offers.discounts.Discount;

import java.time.LocalDate;

public class DiscountOffer extends ExpirableOffer {
    private Discount discount;

    public DiscountOffer(LocalDate to, String name, Condition condition, Discount discount) {
        super(to, name, condition);
        this.discount = discount;
    }

    @Override
    public void perform(Check check) {
        int discountvalue = discount.get(check);
        check.addDiscount(this.getName(), discountvalue);
    }
}
