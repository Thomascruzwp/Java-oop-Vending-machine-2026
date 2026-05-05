package gui;

import core.*;
import product.*;
import hardware.Coin;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class VendingMachineGUI extends JFrame {

    private VendingMachine machine;

    private JLabel status;
    private JLabel balance;
    private JLabel cartLabel;

    // ================= CART (ONLY CODES) =================
    private List<String> cart = new ArrayList<>();

    // ================= ITEM MODEL =================
    private static class Item {
        String name;
        double price;

        Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    private Map<String, Item> items = new LinkedHashMap<>();

    public VendingMachineGUI() {

        machine = new VendingMachine("School");

        // ================= ITEMS =================
        register("A1", "Coke", 1.75);
        register("A2", "Diet Coke", 1.85);
        register("A3", "Pepsi", 1.65);
        register("A4", "Sprite", 1.70);
        register("A5", "Fanta", 1.80);
        register("A6", "Dr Pepper", 1.90);
        register("A7", "Water", 1.00);
        register("A8", "Sparkling Water", 1.25);
        register("A9", "Gatorade", 2.25);
        register("A10", "Powerade", 2.35);

        register("B1", "Lays Chips", 1.50);
        register("B2", "Doritos", 1.60);
        register("B3", "Cheetos", 1.55);
        register("B4", "Ruffles", 1.65);
        register("B5", "Pringles", 2.00);

        register("C1", "Snickers", 1.75);
        register("C2", "KitKat", 1.80);
        register("C3", "Twix", 1.85);
        register("C4", "M&Ms", 1.95);
        register("C5", "Skittles", 1.90);

        register("D1", "Oreo", 2.10);
        register("D2", "Granola Bar", 1.40);
        register("D3", "Trail Mix", 2.30);
        register("D4", "Peanuts", 1.20);
        register("D5", "Crackers", 1.25);

        register("E1", "Pretzels", 1.45);
        register("E2", "Rice Krispies", 1.35);
        register("E3", "Cheese Crackers", 1.30);
        register("E4", "Beef Jerky", 2.75);
        register("E5", "Iced Tea", 2.10);

        // ================= WINDOW =================
        setTitle("Vending Machine");
        setSize(750, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= TOP =================
        JPanel top = new JPanel(new GridLayout(3, 1));

        status = new JLabel("Insert Coins");
        balance = new JLabel("Balance: $0.00");
        cartLabel = new JLabel("Cart: empty");

        top.add(status);
        top.add(balance);
        top.add(cartLabel);

        add(top, BorderLayout.NORTH);

        // ================= GRID =================
        JPanel grid = new JPanel(new GridLayout(0, 5, 5, 5));

        // ================= COINS =================
        JButton c1 = new JButton("$1 Coin");
        JButton c50 = new JButton("$0.50 Coin");

        c1.addActionListener(e -> {
            machine.insertCoin(new Coin(1.0));
            machine.setMessage("Inserted $1");
            updateUI();
        });

        c50.addActionListener(e -> {
            machine.insertCoin(new Coin(0.5));
            machine.setMessage("Inserted $0.50");
            updateUI();
        });

        grid.add(c1);
        grid.add(c50);

        // ================= ITEMS =================
        for (String code : items.keySet()) {
            addButton(grid, code);
        }

        // ================= BUY =================
        JButton buy = new JButton("BUY ALL");
        buy.setBackground(Color.GREEN);

        buy.addActionListener(e -> {

            double total = calculateTotal();

            if (machine.getBalance() < total) {
                machine.setMessage("Not enough money");
                updateUI();
                return;
            }

            machine.setMessage("Processing...");
            updateUI();

            for (String code : cart) {
                Item item = items.get(code);

                machine.setMessage("Dispensing " + code + " - " + item.name);
                updateUI();

                try { Thread.sleep(300); } catch (Exception ignored) {}
            }

            machine.addBalance(-total);

            cart.clear();
            updateCart();
            updateUI();

            machine.setMessage("Order complete");
        });

        grid.add(buy);

        // ================= CANCEL =================
        JButton cancel = new JButton("CANCEL");
        cancel.setBackground(Color.RED);

        cancel.addActionListener(e -> {
            cart.clear();
            machine.setMessage("Cart cleared");
            updateCart();
            updateUI();
        });

        grid.add(cancel);

        add(grid, BorderLayout.CENTER);

        setVisible(true);
    }

    // ================= REGISTER =================
    private void register(String code, String name, double price) {
        items.put(code, new Item(name, price));
    }

    // ================= BUTTON =================
    private void addButton(JPanel grid, String code) {

        Item item = items.get(code);

        JButton btn = new JButton("<html><center>"
                + code + " - " + item.name
                + "<br>$" + item.price
                + "</center></html>");

        btn.setBackground(new Color(
                (int)(Math.random() * 200 + 50),
                (int)(Math.random() * 200 + 50),
                (int)(Math.random() * 200 + 50)
        ));

        btn.addActionListener(e -> {
            cart.add(code);
            machine.setMessage(item.name + " (" + code + ") added");
            updateCart();
            updateUI();
        });

        grid.add(btn);
    }

    // ================= CART =================
    private void updateCart() {

        if (cart.isEmpty()) {
            cartLabel.setText("Cart: empty");
            return;
        }

        StringBuilder sb = new StringBuilder("<html>Cart:<br>");
        double total = 0;

        for (String code : cart) {
            Item item = items.get(code);

            sb.append(code).append(" - ").append(item.name).append("<br>");
            total += item.price;
        }

        sb.append("<br>Total: $").append(String.format("%.2f", total)).append("</html>");

        cartLabel.setText(sb.toString());
    }

    // ================= TOTAL =================
    private double calculateTotal() {
        double total = 0;

        for (String code : cart) {
            total += items.get(code).price;
        }

        return total;
    }

    // ================= UI =================
    private void updateUI() {
        status.setText(machine.getMessage());
        balance.setText("Balance: $" + String.format("%.2f", machine.getBalance()));
    }
}