package checkout;

import java.time.LocalDate;

public abstract class Offer {
    private LocalDate to;

    Offer(LocalDate to) {
        this.to = to;
    }

    public abstract int getPoints(Check check);

    private boolean isNotExpired() {
        return LocalDate.now().isBefore(to);
    }

    public void apply(Check check) {
        if (isNotExpired())
            check.addPoints(getPoints(check));
    }
}
