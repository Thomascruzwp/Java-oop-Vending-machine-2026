package product;

public class Snack extends Product {

    private String name;
    private double price;

    public Snack(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void dispense() {
        System.out.println("Dispensing Snack: " + name);
    }

    public double getPrice() {
        return price;
    }
}