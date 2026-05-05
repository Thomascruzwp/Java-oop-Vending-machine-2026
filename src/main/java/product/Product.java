package product;

public abstract class Product {

    protected String name;
    protected double price;
    protected int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public boolean inStock() {
        return stock > 0;
    }

    public void reduceStock() {
        if (stock > 0) stock--;
    }

    public abstract void dispense();
}