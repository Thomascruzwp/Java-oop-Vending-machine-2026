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
    private List<Double> money = new ArrayList<>();

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

        // ================= ORIGINAL ITEMS (A1 REMOVED ONLY) =================

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
        setSize(950, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= TOP =================
        JPanel top = new JPanel(new GridLayout(2, 1));

        status = new JLabel("Insert Money");
        balance = new JLabel("Balance: $0.00");

        top.add(status);
        top.add(balance);

        add(top, BorderLayout.NORTH);

        // ================= CART =================
        cartArea = new JTextArea();
        cartArea.setEditable(false);
        cartArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(cartArea);
        scroll.setPreferredSize(new Dimension(220, 0));

        add(scroll, BorderLayout.EAST);

        // ================= CENTER =================
        JPanel center = new JPanel(new GridLayout(1, 2));

        // ================= MONEY PANEL (COINS + BILLS TOGETHER) =================
        JPanel moneyPanel = new JPanel(new GridLayout(0, 1));
        moneyPanel.setBorder(BorderFactory.createTitledBorder("Money"));

        // coins
        addMoney(moneyPanel, "$1.00", 1.00);
        addMoney(moneyPanel, "$0.50", 0.50);
        addMoney(moneyPanel, "$0.25", 0.25);
        addMoney(moneyPanel, "$0.10", 0.10);
        addMoney(moneyPanel, "$0.05", 0.05);
        addMoney(moneyPanel, "$0.01", 0.01);

        // bills
        addMoney(moneyPanel, "$1 Bill", 1.00);
        addMoney(moneyPanel, "$5 Bill", 5.00);
        addMoney(moneyPanel, "$10 Bill", 10.00);
        addMoney(moneyPanel, "$20 Bill", 20.00);

        // ================= ITEMS =================
        JPanel itemPanel = new JPanel(new GridLayout(0, 2));
        itemPanel.setBorder(BorderFactory.createTitledBorder("Items"));

        for (String code : items.keySet()) {
            addItemButton(itemPanel, code);
        }

        center.add(moneyPanel);
        center.add(itemPanel);

        add(center, BorderLayout.CENTER);

        // ================= BUY =================
        JButton buy = new JButton("BUY ALL");
        buy.setBackground(Color.GREEN);

        buy.addActionListener(e -> {

            double total = calculateTotal();
            double moneyTotal = getBalance();

            if (moneyTotal < total) {
                status.setText("Not enough money");
                return;
            }

            double change = moneyTotal - total;

            for (String code : cart) {
                status.setText("Dispensing " + code);
                try { Thread.sleep(20); } catch (Exception ignored) {}
            }

            cart.clear();
            money.clear();

            updateCart();
            updateUI();

            status.setText("Change: $" + String.format("%.2f", change));
        });

        add(buy, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ================= REGISTER =================
    private void register(String code, String name, double price) {
        items.put(code, new Item(name, price));
    }

    // ================= MONEY =================
    private void addMoney(JPanel panel, String label, double value) {
        JButton btn = new JButton(label);

        btn.addActionListener(e -> {
            money.add(value);
            updateUI();
        });

        panel.add(btn);
    }

    // ================= ITEMS =================
    private void addItemButton(JPanel panel, String code) {

        Item item = items.get(code);

        JButton btn = new JButton(code + " - " + item.name + " $" + String.format("%.2f", item.price));

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
        for (double m : money) {
            total += m;
        }
        return total;
    }

    // ================= UI =================
    private void updateUI() {
        balance.setText("Balance: $" + String.format("%.2f", getBalance()));
    }
}