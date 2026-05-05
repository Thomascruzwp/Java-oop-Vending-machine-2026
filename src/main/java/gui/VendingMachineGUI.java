package gui;

import core.VendingMachine;
import core.ProductSlot;
import product.*;
import hardware.Coin;

import javax.swing.*;
import java.awt.*;

public class VendingMachineGUI extends JFrame {

    private VendingMachine machine;
    private JLabel status;
    private JLabel balanceLabel;

    public VendingMachineGUI() {

        // ===== MACHINE =====
        machine = new VendingMachine("School");

        // ===== INVENTORY =====
        machine.getInventory().addProduct(new ProductSlot(1), new Drink("Coke", 1.50, 10));
        machine.getInventory().addProduct(new ProductSlot(2), new Drink("Pepsi", 1.50, 8));
        machine.getInventory().addProduct(new ProductSlot(3), new Drink("Sprite", 1.50, 7));
        machine.getInventory().addProduct(new ProductSlot(4), new Drink("Water", 1.00, 20));

        machine.getInventory().addProduct(new ProductSlot(5), new Snack("Chips", 1.00, 10));
        machine.getInventory().addProduct(new ProductSlot(6), new Snack("Chocolate", 1.25, 8));
        machine.getInventory().addProduct(new ProductSlot(7), new Snack("Gum", 0.75, 25));

        // ===== WINDOW =====
        setTitle("Vending Machine");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));

        status = new JLabel("Insert Coins");
        balanceLabel = new JLabel("Balance: $0.00");

        add(status);
        add(balanceLabel);

        // ===== COIN BUTTONS =====
        JButton coin1 = new JButton("Insert $1.00");
        JButton coin50 = new JButton("Insert $0.50");

        add(coin1);
        add(coin50);

        coin1.addActionListener(e -> {
            machine.insertCoin(new Coin(1.0));
            updateUI("Inserted $1.00");
        });

        coin50.addActionListener(e -> {
            machine.insertCoin(new Coin(0.5));
            updateUI("Inserted $0.50");
        });

        // ===== ITEM BUTTONS =====
        addButton("Coke", 1);
        addButton("Pepsi", 2);
        addButton("Sprite", 3);
        addButton("Water", 4);
        addButton("Chips", 5);
        addButton("Chocolate", 6);
        addButton("Gum", 7);

        setVisible(true);
    }

    // ===== BUTTON HELPER =====
    private void addButton(String name, int id) {
        JButton btn = new JButton("Buy " + name);

        btn.addActionListener(e -> {
            machine.selectItem(id);
            machine.dispense();
            updateUI("Dispensed " + name);
        });

        add(btn);
    }

    // ===== UI UPDATE =====
    private void updateUI(String message) {
        status.setText(message);
        balanceLabel.setText("Balance: $" + machine.getBalance());
    }
}