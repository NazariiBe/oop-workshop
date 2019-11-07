package checkout.offers;

import checkout.Check;
import checkout.offers.conditions.Condition;
import checkout.offers.rewards.Reward;

import java.time.LocalDate;

public class BonusOffer extends ExpirableOffer {
    private Reward reward;

    public BonusOffer(LocalDate to, String name, Condition condition, Reward reward) {
        super(to, name, condition);
        this.reward = reward;
    }

    @Override
    public void perform(Check check) {
        int points = reward.get(check);
        check.addReward(this.getName(), points);
    }
}
