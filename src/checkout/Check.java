package checkout;

import java.util.ArrayList;
import java.util.List;

public class Check {
    private final List<Product> products = new ArrayList<>();

    private final List<Entry> discounts = new ArrayList<>();
    private final List<Entry> rewards = new ArrayList<>();

    public int getTotalCost() {
        int totalCost = 0;
        for (Product product : this.products) {
            totalCost += product.getPrice();
        }
        return totalCost - getAllDiscouts();
    }

    void addProduct(Product product) {
        products.add(product);
    }

    int getAllPoints() {
        return getSum(this.rewards);
    }

    int getAllDiscouts() {
        return getSum(this.discounts);
    }

    private int getSum(List<Entry> list) {
        int sum = 0;
        for (Entry entry: list) {
            sum += entry.getValue();
        }
        return sum;
    }

    public int getCostByCategory(Category category) {
        return products.stream()
                .filter(p -> p.getCategory() == category)
                .mapToInt(Product::getPrice)
                .reduce(0, Integer::sum);
    }

    public int getAmountByid(int id) {
        return (int) products.stream()
                .filter(p -> p.getId() == id)
                .count();
    }

    public void addReward(String name, int value) {
        rewards.add(new Entry(name, value));
    }

    public void addDiscount(String name, int discountvalue) {
        discounts.add(new Entry(name, discountvalue));
    }

    private static class Entry {
        private String name;
        private int value;

        public Entry(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }
}