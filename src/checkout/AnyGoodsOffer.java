package checkout;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {
    private final int totalCost;
    private final int points;

    public AnyGoodsOffer(LocalDate to, int totalCost, int points) {
        super(to);
        this.totalCost = totalCost;
        this.points = points;
    }

    @Override
    public int getPoints(Check check) {
        if (this.totalCost <= check.getTotalCost()) return this.points;
        return 0;
    }
}
