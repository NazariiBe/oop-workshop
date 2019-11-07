package checkout.offers.rewards;

import checkout.Check;

public class FactorReward implements Reward {
    private float factor;

    public FactorReward(float factor) {
        this.factor = factor;
    }

    @Override
    public int get(Check check) {
        return (int) (check.getTotalCost() * factor);
    }
}
