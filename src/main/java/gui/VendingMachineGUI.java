package gui;

import core.*;
import product.*;
import hardware.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class VendingMachineGUI {

    private VendingMachine machine;
    private JLabel balanceLabel;
    private JLabel messageLabel;

    public VendingMachineGUI() {

        // ================= INVENTORY SETUP (UML CORRECT) =================
        Map<ProductSlot, Product> products = new HashMap<>();

        products.put(new ProductSlot(1), new Drink("Coke", 1.5));
        products.put(new ProductSlot(2), new Snack("Chips", 2.0));
        products.put(new ProductSlot(3), new Drink("Water", 1.0));

        Inventory inventory = new Inventory(products);

        // Machine with location (UML attribute)
        machine = new VendingMachine("Campus", inventory);

        // ================= GUI =================
        JFrame frame = new JFrame("Vending Machine UML Demo");
        frame.setSize(450, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ===== DISPLAY =====
        JPanel top = new JPanel(new GridLayout(2,1));
        top.setBackground(Color.BLACK);

        balanceLabel = new JLabel("Balance: $0.00", SwingConstants.CENTER);
        messageLabel = new JLabel("Insert Coin", SwingConstants.CENTER);

        balanceLabel.setForeground(Color.WHITE);
        messageLabel.setForeground(Color.GREEN);

        top.add(balanceLabel);
        top.add(messageLabel);

        frame.add(top, BorderLayout.NORTH);

        // ===== PRODUCTS =====
        JPanel center = new JPanel(new GridLayout(2,2,10,10));
        center.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JButton coke = new JButton("Coke $1.5");
        JButton chips = new JButton("Chips $2");
        JButton water = new JButton("Water $1");

        coke.addActionListener(e -> selectItem(1));
        chips.addActionListener(e -> selectItem(2));
        water.addActionListener(e -> selectItem(3));

        center.add(coke);
        center.add(chips);
        center.add(water);

        frame.add(center, BorderLayout.CENTER);

        // ===== CONTROLS =====
        JPanel bottom = new JPanel(new GridLayout(1,2,10,10));

        JButton insert = new JButton("Insert $1");
        JButton cancel = new JButton("Cancel");

        insert.setBackground(Color.ORANGE);
        cancel.setBackground(Color.RED);

        insert.addActionListener(e -> {
            machine.insertCoin(new Coin(1.0));   // ✔ UML correct
            update("Coin inserted");
        });

        cancel.addActionListener(e -> {
            machine.reset();
            update("Transaction cancelled");
        });

        bottom.add(insert);
        bottom.add(cancel);

        frame.add(bottom, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // ================= SELECT ITEM =================
    private void selectItem(int id) {
        machine.selectItem(id);   // ✔ UML correct
        update("Item selected: " + id);
    }

    // ================= UPDATE UI =================
    private void update(String msg) {
        balanceLabel.setText("Balance: $" + machine.getBalance());
        messageLabel.setText(msg);
    }
}