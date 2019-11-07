package checkout.offers.rewards;

import checkout.Check;

public class FlatReward implements Reward {
    private int flat;

    public FlatReward(int flat) {
        this.flat = flat;
    }

    @Override
    public int get(Check check) {
        return flat;
    }
}
