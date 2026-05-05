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
        machine.getInventory().addProduct(new ProductSlot(1), new Drink("Coke", 1.75, 10));
        machine.getInventory().addProduct(new ProductSlot(2), new Drink("Diet Coke", 1.85, 8));
        machine.getInventory().addProduct(new ProductSlot(3), new Drink("Pepsi", 1.65, 10));
        machine.getInventory().addProduct(new ProductSlot(4), new Drink("Sprite", 1.70, 10));
        machine.getInventory().addProduct(new ProductSlot(5), new Drink("Fanta", 1.80, 10));
        machine.getInventory().addProduct(new ProductSlot(6), new Drink("Dr Pepper", 1.90, 8));
        machine.getInventory().addProduct(new ProductSlot(7), new Drink("Water", 1.00, 20));
        machine.getInventory().addProduct(new ProductSlot(8), new Drink("Sparkling Water", 1.25, 12));
        machine.getInventory().addProduct(new ProductSlot(9), new Drink("Gatorade", 2.25, 15));
        machine.getInventory().addProduct(new ProductSlot(10), new Drink("Powerade", 2.35, 12));
        machine.getInventory().addProduct(new ProductSlot(30), new Drink("Iced Tea", 2.10, 12));

        machine.getInventory().addProduct(new ProductSlot(11), new Snack("Lays Chips", 1.50, 15));
        machine.getInventory().addProduct(new ProductSlot(12), new Snack("Doritos", 1.60, 15));
        machine.getInventory().addProduct(new ProductSlot(13), new Snack("Cheetos", 1.55, 15));
        machine.getInventory().addProduct(new ProductSlot(14), new Snack("Ruffles", 1.65, 12));
        machine.getInventory().addProduct(new ProductSlot(15), new Snack("Pringles", 2.00, 10));

        machine.getInventory().addProduct(new ProductSlot(16), new Snack("Snickers", 1.75, 12));
        machine.getInventory().addProduct(new ProductSlot(17), new Snack("KitKat", 1.80, 12));
        machine.getInventory().addProduct(new ProductSlot(18), new Snack("Twix", 1.85, 10));
        machine.getInventory().addProduct(new ProductSlot(19), new Snack("M&Ms", 1.95, 15));
        machine.getInventory().addProduct(new ProductSlot(20), new Snack("Skittles", 1.90, 15));

        machine.getInventory().addProduct(new ProductSlot(21), new Snack("Oreo", 2.10, 10));
        machine.getInventory().addProduct(new ProductSlot(22), new Snack("Granola Bar", 1.40, 12));
        machine.getInventory().addProduct(new ProductSlot(23), new Snack("Trail Mix", 2.30, 10));
        machine.getInventory().addProduct(new ProductSlot(24), new Snack("Peanuts", 1.20, 18));
        machine.getInventory().addProduct(new ProductSlot(25), new Snack("Crackers", 1.25, 15));

        machine.getInventory().addProduct(new ProductSlot(26), new Snack("Pretzels", 1.45, 10));
        machine.getInventory().addProduct(new ProductSlot(27), new Snack("Rice Krispies", 1.35, 12));
        machine.getInventory().addProduct(new ProductSlot(28), new Snack("Cheese Crackers", 1.30, 15));
        machine.getInventory().addProduct(new ProductSlot(29), new Snack("Beef Jerky", 2.75, 8));

        // ================= WINDOW =================
        setTitle("Vending Machine");
        setSize(650, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ================= TOP PANEL =================
        JPanel topPanel = new JPanel(new GridLayout(2, 1));

        status = new JLabel("Insert Coins");
        balance = new JLabel("Balance: $0.00");

        status.setFont(new Font("Arial", Font.BOLD, 16));
        balance.setFont(new Font("Arial", Font.PLAIN, 14));

        topPanel.add(status);
        topPanel.add(balance);

        add(topPanel, BorderLayout.NORTH);

        // ================= GRID =================
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(0, 5, 5, 5));

        // ================= COINS =================
        JButton coin1 = new JButton("$1 Coin");
        JButton coin50 = new JButton("$0.50 Coin");

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

        grid.add(coin1);
        grid.add(coin50);

        // ================= ITEMS =================
        addButton(grid, "Coke", 1, 1.75);
        addButton(grid, "Diet Coke", 2, 1.85);
        addButton(grid, "Pepsi", 3, 1.65);
        addButton(grid, "Sprite", 4, 1.70);
        addButton(grid, "Fanta", 5, 1.80);
        addButton(grid, "Dr Pepper", 6, 1.90);
        addButton(grid, "Water", 7, 1.00);
        addButton(grid, "Sparkling Water", 8, 1.25);
        addButton(grid, "Gatorade", 9, 2.25);
        addButton(grid, "Powerade", 10, 2.35);

        addButton(grid, "Lays Chips", 11, 1.50);
        addButton(grid, "Doritos", 12, 1.60);
        addButton(grid, "Cheetos", 13, 1.55);
        addButton(grid, "Ruffles", 14, 1.65);
        addButton(grid, "Pringles", 15, 2.00);

        addButton(grid, "Snickers", 16, 1.75);
        addButton(grid, "KitKat", 17, 1.80);
        addButton(grid, "Twix", 18, 1.85);
        addButton(grid, "M&Ms", 19, 1.95);
        addButton(grid, "Skittles", 20, 1.90);

        addButton(grid, "Oreo", 21, 2.10);
        addButton(grid, "Granola Bar", 22, 1.40);
        addButton(grid, "Trail Mix", 23, 2.30);
        addButton(grid, "Peanuts", 24, 1.20);
        addButton(grid, "Crackers", 25, 1.25);

        addButton(grid, "Pretzels", 26, 1.45);
        addButton(grid, "Rice Krispies", 27, 1.35);
        addButton(grid, "Cheese Crackers", 28, 1.30);
        addButton(grid, "Beef Jerky", 29, 2.75);

        // ================= DONE BUTTON =================
        JButton done = new JButton("DONE");

        done.addActionListener(e -> {
            machine.reset();
            machine.setMessage("Session ended. Thank you!");
            updateUI();
        });

        grid.add(done);

        add(grid, BorderLayout.CENTER);

        setVisible(true);
    }

    // ================= BUTTON =================
    private void addButton(JPanel grid, String name, int id, double price) {

        JButton btn = new JButton("<html><center>" + name + "<br>$" + price + "</center></html>");

        btn.setPreferredSize(new Dimension(100, 100));
        btn.setFont(new Font("Arial", Font.BOLD, 10));
        btn.setFocusPainted(false);

        btn.setBackground(new Color(173, 216, 230));

        btn.addActionListener(e -> {

            if (machine.getBalance() < price) {
                machine.setMessage("Not enough money for " + name);
                updateUI();
                return;
            }

            machine.selectItem(id);

            machine.setMessage("Dispensing " + name + "...");

            machine.addBalance(-price);

            updateUI();

            machine.setMessage("Dispensed " + name +
                    " | Remaining: $" + String.format("%.2f", machine.getBalance()));

            updateUI();
        });

        grid.add(btn);
    }

    // ================= UI =================
    private void updateUI() {
        status.setText(machine.getMessage());
        balance.setText("Balance: $" + String.format("%.2f", machine.getBalance()));
    }
}