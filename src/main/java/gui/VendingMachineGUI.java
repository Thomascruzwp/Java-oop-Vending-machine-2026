package gui;

import core.VendingMachine;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class VendingMachineGUI extends JFrame {

    private VendingMachine machine;

    private JLabel status;
    private JLabel balanceLabel;

    private JTextArea cartArea;

    private List<String> cart = new ArrayList<>();
    private List<Integer> insertedCoins = new ArrayList<>();

    // ================= ITEM MODEL =================
    private static class Item {
        String name;
        int price; // cents

        Item(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }

    private Map<String, Item> items = new LinkedHashMap<>();

    public VendingMachineGUI() {

        machine = new VendingMachine("School");

        // ================= ITEMS (CENTS) =================
        register("A1", "Chicken Sandwich", 250);
        register("A2", "Diet Coke", 185);
        register("A3", "Pepsi", 165);
        register("A4", "Sprite", 170);
        register("A5", "Fanta", 180);
        register("A6", "Dr Pepper", 190);
        register("A7", "Water", 100);
        register("A8", "Sparkling Water", 125);
        register("A9", "Gatorade", 225);
        register("A10", "Powerade", 235);

        register("C1", "Snickers", 175);
        register("E2", "Rice Krispies", 135);

        // ================= WINDOW =================
        setTitle("Vending Machine");
        setSize(850, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= TOP =================
        JPanel top = new JPanel(new GridLayout(2, 1));

        status = new JLabel("Insert Coins");
        balanceLabel = new JLabel("Balance: $0.00");

        top.add(status);
        top.add(balanceLabel);

        add(top, BorderLayout.NORTH);

        // ================= CART (NO SHRINK FIX) =================
        cartArea = new JTextArea();
        cartArea.setEditable(false);

        JScrollPane scroll = new JScrollPane(cartArea);
        scroll.setPreferredSize(new Dimension(200, 300));

        add(scroll, BorderLayout.EAST);

        // ================= GRID =================
        JPanel grid = new JPanel(new GridLayout(0, 4, 8, 8));

        // ================= COINS =================
        addCoin(grid, "$1.00", 100);
        addCoin(grid, "$0.50", 50);
        addCoin(grid, "$0.25", 25);
        addCoin(grid, "$0.10", 10);
        addCoin(grid, "$0.05", 5);
        addCoin(grid, "$0.01", 1);

        // ================= ITEMS =================
        for (String code : items.keySet()) {
            addButton(grid, code);
        }

        // ================= BUY =================
        JButton buy = new JButton("BUY ALL");
        buy.setBackground(Color.GREEN);

        buy.addActionListener(e -> {

            int total = calculateTotal();
            int money = getBalance();

            if (money < total) {
                status.setText("Not enough money");
                return;
            }

            int change = money - total;

            for (String code : cart) {
                status.setText("Dispensing " + code);
                try { Thread.sleep(60); } catch (Exception ignored) {}
            }

            cart.clear();
            insertedCoins.clear();
            updateCart();

            status.setText("Change: $" + format(change));
            updateUI();
        });

        grid.add(buy);

        // ================= CANCEL =================
        JButton cancel = new JButton("CANCEL");
        cancel.setBackground(Color.RED);

        cancel.addActionListener(e -> {

            int refund = getBalance();

            cart.clear();
            insertedCoins.clear();

            updateCart();

            status.setText("Refunded: $" + format(refund));
            updateUI();
        });

        grid.add(cancel);

        add(grid, BorderLayout.CENTER);

        setVisible(true);
    }

    // ================= HELPERS =================
    private void register(String code, String name, int price) {
        items.put(code, new Item(name, price));
    }

    private void addCoin(JPanel panel, String label, int value) {

        JButton btn = new JButton(label);

        btn.addActionListener(e -> {
            insertedCoins.add(value);
            updateUI();
        });

        panel.add(btn);
    }

    private void addButton(JPanel panel, String code) {

        Item item = items.get(code);

        JButton btn = new JButton("<html><center>"
                + code + "<br>"
                + item.name + "<br>"
                + "$" + format(item.price)
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

        StringBuilder sb = new StringBuilder();

        for (String code : cart) {
            sb.append(code).append("\n");
        }

        sb.append("\nTotal: $").append(format(calculateTotal()));

        cartArea.setText(sb.toString());
    }

    // ================= CALCULATIONS =================
    private int calculateTotal() {
        int total = 0;
        for (String code : cart) {
            total += items.get(code).price;
        }
        return total;
    }

    private int getBalance() {
        int total = 0;
        for (int c : insertedCoins) {
            total += c;
        }
        return total;
    }

    // ================= FORMAT =================
    private String format(int cents) {
        return String.format("%.2f", cents / 100.0);
    }

    // ================= UI =================
    private void updateUI() {
        balanceLabel.setText("Balance: $" + format(getBalance()));
    }
}