package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineGUI extends JFrame {

    private JLabel status;
    private JLabel balance;

    private JTextArea cartArea;

    private List<String> cart = new ArrayList<>();
    private List<Double> coins = new ArrayList<>();

    // ================= ITEM =================
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

        // ================= ITEMS (A1 REMOVED) =================
        register("A2", "Diet Coke", 1.85);
        register("A3", "Pepsi", 1.65);
        register("A4", "Sprite", 1.70);
        register("A5", "Fanta Orange", 1.80);
        register("A6", "Dr Pepper", 1.90);
        register("A7", "Water Bottle", 1.00);
        register("A8", "Sparkling Water", 1.25);
        register("A9", "Gatorade Blue", 2.25);
        register("A10", "Powerade Red", 2.35);

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

        register("D1", "Oreo Cookies", 2.10);
        register("D2", "Granola Bar", 1.40);
        register("D3", "Trail Mix", 2.30);
        register("D4", "Salted Peanuts", 1.20);
        register("D5", "Salt Crackers", 1.25);

        register("E1", "Pretzels", 1.45);
        register("E2", "Rice Krispies", 1.35);
        register("E3", "Cheese Crackers", 1.30);
        register("E4", "Beef Jerky", 2.75);
        register("E5", "Iced Tea", 2.10);

        // ================= WINDOW =================
        setTitle("Vending Machine");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= TOP =================
        JPanel top = new JPanel(new GridLayout(2, 1));

        status = new JLabel("Insert Coins");
        balance = new JLabel("Balance: $0.00");

        top.add(status);
        top.add(balance);

        add(top, BorderLayout.NORTH);

        // ================= CART (FIXED UI) =================
        cartArea = new JTextArea();
        cartArea.setEditable(false);
        cartArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(cartArea);
        scroll.setPreferredSize(new Dimension(220, 0));

        add(scroll, BorderLayout.EAST);

        // ================= GRID =================
        JPanel grid = new JPanel(new GridLayout(0, 4, 8, 8));

        // ================= COINS =================
        addCoin(grid, "$1.00", 1.00);
        addCoin(grid, "$0.50", 0.50);
        addCoin(grid, "$0.25", 0.25);
        addCoin(grid, "$0.10", 0.10);
        addCoin(grid, "$0.05", 0.05);
        addCoin(grid, "$0.01", 0.01);

        // ================= ITEMS =================
        for (String code : items.keySet()) {
            addItemButton(grid, code);
        }

        // ================= BUY =================
        JButton buy = new JButton("BUY ALL");
        buy.setBackground(Color.GREEN);

        buy.addActionListener(e -> {

            double total = calculateTotal();
            double money = getBalance();

            if (money < total) {
                status.setText("Not enough money");
                return;
            }

            double change = money - total;

            for (String code : cart) {
                status.setText("Dispensing " + code);
                try { Thread.sleep(30); } catch (Exception ignored) {}
            }

            cart.clear();
            coins.clear();

            updateCart();
            updateUI();

            status.setText("Change: $" + String.format("%.2f", change));
        });

        grid.add(buy);

        // ================= CANCEL =================
        JButton cancel = new JButton("CANCEL");
        cancel.setBackground(Color.RED);

        cancel.addActionListener(e -> {

            double refund = getBalance();

            cart.clear();
            coins.clear();

            updateCart();
            updateUI();

            status.setText("Refunded: $" + String.format("%.2f", refund));
        });

        grid.add(cancel);

        add(grid, BorderLayout.CENTER);

        setVisible(true);
    }

    // ================= REGISTER =================
    private void register(String code, String name, double price) {
        items.put(code, new Item(name, price));
    }

    // ================= COINS =================
    private void addCoin(JPanel panel, String label, double value) {
        JButton btn = new JButton(label);

        btn.addActionListener(e -> {
            coins.add(value);
            updateUI();
        });

        panel.add(btn);
    }

    // ================= ITEMS =================
    private void addItemButton(JPanel panel, String code) {

        Item item = items.get(code);

        JButton btn = new JButton("<html><center>"
                + code + "<br>"
                + item.name + "<br>"
                + String.format("$%.2f", item.price)
                + "</center></html>");

        btn.setPreferredSize(new Dimension(160, 110));

        btn.addActionListener(e -> {
            cart.add(code);
            updateCart();
        });

        panel.add(btn);
    }

    // ================= CART =================
    private void updateCart() {

        if (cart.isEmpty()) {
            cartArea.setText("Cart: empty");
            return;
        }

        StringBuilder sb = new StringBuilder();
        double total = 0;

        for (String code : cart) {
            sb.append(code).append("\n");
            total += items.get(code).price;
        }

        sb.append("\nTotal: $").append(String.format("%.2f", total));

        cartArea.setText(sb.toString());
    }

    // ================= TOTAL =================
    private double calculateTotal() {
        double total = 0;
        for (String code : cart) {
            total += items.get(code).price;
        }
        return total;
    }

    // ================= BALANCE =================
    private double getBalance() {
        double total = 0;
        for (double c : coins) {
            total += c;
        }
        return total;
    }

    // ================= UI =================
    private void updateUI() {
        balance.setText("Balance: $" + String.format("%.2f", getBalance()));
    }
}