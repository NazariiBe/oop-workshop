package checkout.offers;

import checkout.Check;

public interface Offer {
    boolean apply(Check check);
    String getName();
}
