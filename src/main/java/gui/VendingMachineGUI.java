package gui;

import core.*;
import product.*;
import hardware.Coin;

import javax.swing.*;
import java.awt.*;

public class VendingMachineGUI extends JFrame {

    private VendingMachine machine;
    private JLabel status;
    private JLabel balance;

    public VendingMachineGUI() {

        machine = new VendingMachine("School");

        // ================= INVENTORY =================
        machine.getInventory().addProduct(new ProductSlot(1), new Drink("Coke", 1.50, 10));
        machine.getInventory().addProduct(new ProductSlot(2), new Drink("Diet Coke", 1.50, 8));
        machine.getInventory().addProduct(new ProductSlot(3), new Drink("Pepsi", 1.50, 10));
        machine.getInventory().addProduct(new ProductSlot(4), new Drink("Sprite", 1.50, 10));
        machine.getInventory().addProduct(new ProductSlot(5), new Drink("Fanta", 1.50, 10));
        machine.getInventory().addProduct(new ProductSlot(6), new Drink("Dr Pepper", 1.50, 8));
        machine.getInventory().addProduct(new ProductSlot(7), new Drink("Water", 1.00, 20));
        machine.getInventory().addProduct(new ProductSlot(8), new Drink("Sparkling Water", 1.25, 12));
        machine.getInventory().addProduct(new ProductSlot(9), new Drink("Gatorade", 1.75, 15));
        machine.getInventory().addProduct(new ProductSlot(10), new Drink("Powerade", 1.75, 12));

        machine.getInventory().addProduct(new ProductSlot(11), new Snack("Lays Chips", 1.25, 15));
        machine.getInventory().addProduct(new ProductSlot(12), new Snack("Doritos", 1.25, 15));
        machine.getInventory().addProduct(new ProductSlot(13), new Snack("Cheetos", 1.25, 15));
        machine.getInventory().addProduct(new ProductSlot(14), new Snack("Ruffles", 1.25, 12));
        machine.getInventory().addProduct(new ProductSlot(15), new Snack("Pringles", 1.75, 10));

        machine.getInventory().addProduct(new ProductSlot(16), new Snack("Snickers", 1.50, 12));
        machine.getInventory().addProduct(new ProductSlot(17), new Snack("KitKat", 1.50, 12));
        machine.getInventory().addProduct(new ProductSlot(18), new Snack("Twix", 1.50, 10));
        machine.getInventory().addProduct(new ProductSlot(19), new Snack("M&Ms", 1.25, 15));
        machine.getInventory().addProduct(new ProductSlot(20), new Snack("Skittles", 1.25, 15));

        machine.getInventory().addProduct(new ProductSlot(21), new Snack("Oreo", 1.75, 10));
        machine.getInventory().addProduct(new ProductSlot(22), new Snack("Granola Bar", 1.20, 12));
        machine.getInventory().addProduct(new ProductSlot(23), new Snack("Trail Mix", 1.50, 10));
        machine.getInventory().addProduct(new ProductSlot(24), new Snack("Peanuts", 1.00, 18));
        machine.getInventory().addProduct(new ProductSlot(25), new Snack("Crackers", 1.00, 15));

        // ❌ Popcorn REMOVED
        // ✔ Replaced with Pretzels
        machine.getInventory().addProduct(new ProductSlot(26), new Snack("Pretzels", 1.50, 10));

        machine.getInventory().addProduct(new ProductSlot(27), new Snack("Rice Krispies", 1.25, 12));
        machine.getInventory().addProduct(new ProductSlot(28), new Snack("Cheese Crackers", 1.00, 15));
        machine.getInventory().addProduct(new ProductSlot(29), new Snack("Beef Jerky", 2.50, 8));
        machine.getInventory().addProduct(new ProductSlot(30), new Drink("Iced Tea", 1.50, 12));

        // ================= WINDOW =================
        setTitle("Vending Machine");
        setSize(500, 800);
        setLayout(new GridLayout(0, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        status = new JLabel("Insert Coins");
        balance = new JLabel("Balance: $0.00");

        add(status);
        add(balance);

        // ================= COINS =================
        JButton coin1 = new JButton("$1 Coin");
        JButton coin50 = new JButton("$0.50 Coin");

        add(coin1);
        add(coin50);

        coin1.addActionListener(e -> {
            machine.insertCoin(new Coin(1.0));
            machine.setMessage("Inserted $1");
            updateUI();
        });

        coin50.addActionListener(e -> {
            machine.insertCoin(new Coin(0.5));
            machine.setMessage("Inserted $0.50");
            updateUI();
        });

        // ================= ITEMS =================
        addButton("Coke", 1, 1.50);
        addButton("Diet Coke", 2, 1.50);
        addButton("Pepsi", 3, 1.50);
        addButton("Sprite", 4, 1.50);
        addButton("Fanta", 5, 1.50);
        addButton("Dr Pepper", 6, 1.50);
        addButton("Water", 7, 1.00);
        addButton("Sparkling Water", 8, 1.25);
        addButton("Gatorade", 9, 1.75);
        addButton("Powerade", 10, 1.75);

        addButton("Lays", 11, 1.25);
        addButton("Doritos", 12, 1.25);
        addButton("Cheetos", 13, 1.25);
        addButton("Ruffles", 14, 1.25);
        addButton("Pringles", 15, 1.75);

        addButton("Snickers", 16, 1.50);
        addButton("KitKat", 17, 1.50);
        addButton("Twix", 18, 1.50);
        addButton("M&Ms", 19, 1.25);
        addButton("Skittles", 20, 1.25);

        addButton("Oreo", 21, 1.75);
        addButton("Granola Bar", 22, 1.20);
        addButton("Trail Mix", 23, 1.50);
        addButton("Peanuts", 24, 1.00);
        addButton("Crackers", 25, 1.00);

        // ✔ Pretzels instead of Popcorn
        addButton("Pretzels", 26, 1.50);

        addButton("Rice Krispies", 27, 1.25);
        addButton("Cheese Crackers", 28, 1.00);
        addButton("Beef Jerky", 29, 2.50);
        addButton("Iced Tea", 30, 1.50);

        setVisible(true);
    }

    // ================= BUTTON LOGIC =================
    private void addButton(String name, int id, double price) {

        JButton btn = new JButton(name + " ($" + price + ")");

        btn.addActionListener(e -> {

            if (machine.getBalance() < price) {
                machine.setMessage("Not enough money for " + name);
                updateUI();
                return;
            }

            machine.selectItem(id);
            machine.setMessage("Dispensing " + name + "...");
            machine.addBalance(-price);
            machine.dispense();

            updateUI();
        });

        add(btn);
    }

    // ================= UI UPDATE =================
    private void updateUI() {
        status.setText(machine.getMessage());
        balance.setText("Balance: $" + String.format("%.2f", machine.getBalance()));
    }
}