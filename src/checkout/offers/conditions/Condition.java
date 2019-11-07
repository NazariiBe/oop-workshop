package checkout.offers.conditions;

import checkout.Check;

public interface Condition {
    boolean isAllowed(Check check);
}
