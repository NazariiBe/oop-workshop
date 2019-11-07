package checkout;

import checkout.offers.BonusOffer;
import checkout.offers.DiscountOffer;
import checkout.offers.Offer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public class CheckoutService {
    private List<Offer> offers = new ArrayList();
    private Check check;

    public void openCheck() {
        check = new Check();
    }

    public void addProduct(Product product) {
        if (check == null) {
            openCheck();
        }
        check.addProduct(product);
    }

    public Check closeCheck() {
        Check closedCheck = this.check;

        for (Offer offer: offers) {
            if (offer instanceof DiscountOffer) offer.apply(check);
        }
        for (Offer offer: offers) {
            if (offer instanceof BonusOffer) offer.apply(check);
        }

        this.reset();

        return closedCheck;
    }

    public void reset() {
        offers.clear();
        this.check = null;
    }

    public void useOffer(Offer offer) {
        offers.add(offer);
    }
}
