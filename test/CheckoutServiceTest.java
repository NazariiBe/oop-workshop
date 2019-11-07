import checkout.*;
import checkout.offers.BonusOffer;
import checkout.offers.DiscountOffer;
import checkout.offers.Offer;
import checkout.offers.conditions.Condition;
import checkout.offers.conditions.MinimalCostCondition;
import checkout.offers.discounts.FixedDiscount;
import checkout.offers.discounts.PersentDiscount;
import checkout.offers.rewards.FlatReward;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutServiceTest {
    private LocalDate now;
    private LocalDate tomorrow;
    private LocalDate yestarday;

    private CheckoutService service;

    private Product milk;
    private Product bread;

    private Check close() {
        return service.closeCheck();
    }

    private void total(Check check, int value) {
        assertThat(check.getTotalCost(), is(value));
    }

    @BeforeEach
    void start() {
        service = new CheckoutService();
        service.openCheck();

        now = LocalDate.now();
        tomorrow = now.plusDays(1);
        yestarday = now.minusDays(1);

        milk = new Product(0, 7, "Milk", Category.MILK);
        bread = new Product(1, 3, "Bread", Category.BAKERY);
    }

    @Test
    void service_closeCheck() {
        Check check = service.closeCheck();
    }

    @Test
    void addProduct() {
        service.addProduct(milk);
        Check check = close();

        total(check, 7);
    }

    @Test
    void addProduct__Two() {
        service.addProduct(milk);
        service.addProduct(bread);
        Check check = close();

        total(check, 10);
    }

    @Test
    void addProduct__TwoSame() {
        service.addProduct(milk);
        service.addProduct(milk);
        Check check = close();

        total(check, 14);
    }

    @Test
    void addProduct__whileCheckIsClosed() {
        service.addProduct(milk);
        Check check = close();
        service.addProduct(bread);

        total(check, 7);
    }

    private void addMilk() {
        service.addProduct(milk);
    }

    private void addBread() {
        service.addProduct(bread);
    }

    private void addTwo_10() {
        addBread();
        addMilk();
    }

    private void points(Check check, int value) {
        assertThat(check.getAllPoints(), is(value));
    }

    private void discount(Check check, int value) {
        assertThat(check.getAllDiscouts(), is(value));
    }

    private void real(Check check, int value) {
        assertThat(check.getTotalCost() - check.getAllDiscouts(), is(value));
    }

    @Test
    void useOffer__Bonus() {
        addTwo_10();
        service.useOffer(new BonusOffer(
                tomorrow,
                "Test",
                new MinimalCostCondition(10),
                new FlatReward(2)
        ));


        Check check = close();
        points(check, 12);
        total(check, 10);
    }

    @Test
    void useOffer__FlatDiscount() {
        addTwo_10();
        service.useOffer(new DiscountOffer(
                tomorrow,
                "Test",
                new MinimalCostCondition(10),
                new FixedDiscount(5)
        ));

        Check check = close();
        total(check, 10);
        discount(check, 5);
        real(check, 5);
    }

    @Test
    void useOffer__FactorDiscount() {
        addTwo_10();
        service.useOffer(new DiscountOffer(
                tomorrow,
                "Test",
                new MinimalCostCondition(10),
                new PersentDiscount(0.5f)
        ));

        Check check = close();
        total(check, 10);
        discount(check, 5);
        real(check, 5);
    }

    private BonusOffer flatbonus(LocalDate to, Condition condition, int reward) {
        return new BonusOffer(to, "bonus", condition, new FlatReward(reward));
    }

    private void useExpiredBonusOffer(Condition condition, int reward) {
        service.useOffer(flatbonus(yestarday, condition, reward));
    }

    private void useNotExpiredBonusOffer(Condition condition, int reward) {
        service.useOffer(flatbonus(tomorrow, condition, reward));
    }

    private Condition trueCond() {
        return new MinimalCostCondition(0);
    }

    private Condition falseCond() {
        return new MinimalCostCondition(99999);
    }

    @Test
    void useOffer__Expired__TrueCond() {
        addTwo_10();
        useExpiredBonusOffer(trueCond(), 10);

        points(close(), 10);
    }

    @Test
    void useOffer__NotExpired__FalseCond() {
        addTwo_10();
        useNotExpiredBonusOffer(falseCond(), 10);

        points(close(), 10);
    }

    @Test
    void useOffer__NoOrder() {
        addTwo_10();
        addTwo_10();
        useNotExpiredBonusOffer(trueCond(), 10);

        points(close(), 30);
    }
}

























