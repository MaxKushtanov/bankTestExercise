package bank.products.abstracts;

public abstract class Product {
    private final String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
