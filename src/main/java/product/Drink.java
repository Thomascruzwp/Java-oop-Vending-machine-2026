package product;

public class Drink extends Product {

    public Drink(String name, double price, int stock) {
            super(name, price, stock);
                }

                    @Override
                        public void dispense() {
                                System.out.println("Dispensing drink: " + name);
                                    }
                                    }