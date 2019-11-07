package checkout.offers.discounts;

import checkout.Check;

public interface Discount {
    int get(Check check);
}
