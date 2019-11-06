package checkout;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    private final Category category;
    private final int factor;

    public FactorByCategoryOffer(LocalDate to, Category category, int factor) {
        super(to);
        this.category = category;
        this.factor = factor;
    }

    @Override
    public int getPoints(Check check) {
        int points = check.getCostByCategory(this.category);
        return points * (this.factor - 1);
    }
}
