package checkout;

public class Product {
    private int id;
    private int price;
    private String name;
    private Category category;

    public Product(int id, int price, String name, Category category) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
