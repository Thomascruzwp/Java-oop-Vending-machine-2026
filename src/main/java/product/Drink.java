package product;

public class Drink extends Product {

    private String name;
    private double price;

    public Drink(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void dispense() {
        System.out.println("Dispensing Drink: " + name);
    }

    public double getPrice() {
        return price;
    }
}