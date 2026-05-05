package product;

public class Snack extends Product {

    public Snack(String name, double price, int stock) {
        super(name, price, stock);
    }

    @Override
    public void dispense() {
        System.out.println("Dispensing snack: " + name);
    }
}