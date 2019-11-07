package checkout.offers;

import checkout.Check;
import checkout.offers.conditions.Condition;

import java.time.LocalDate;

public abstract class ExpirableOffer implements Expirable, Offer {
    private LocalDate to;
    private String name;
    private Condition condition;

    public ExpirableOffer(LocalDate to, String name, Condition condition) {
        this.to = to;
        this.name = name;
        this.condition = condition;
    }

    public boolean isNotExpired() {
        return LocalDate.now().isBefore(to);
    }

    public boolean apply(Check check) {
        if (isNotExpired() & condition.isAllowed(check)) {
            perform(check);
            return true;
        } else {
            return false;
        }
    }

    public abstract void perform(Check check);

    public String getName() {
        return this.name;
    }
}
