package checkout;

import checkout.offers.BonusOffer;
import checkout.offers.DiscountOffer;
import checkout.offers.Offer;
import checkout.offers.discounts.Discount;
import checkout.offers.discounts.FixedDiscount;
import checkout.offers.rewards.FactorReward;
import checkout.offers.rewards.FlatReward;
import checkout.offers.rewards.Reward;
import com.sun.source.tree.LambdaExpressionTree;

import java.util.ArrayList;
import java.util.List;

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

        for (Offer offer: offers) offer.apply(check);

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
