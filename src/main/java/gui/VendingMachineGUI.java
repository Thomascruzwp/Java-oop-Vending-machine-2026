package gui;

import core.*;
import product.*;
import hardware.Coin;

import javax.swing.*;
import java.awt.*;

public class VendingMachineGUI extends JFrame {

    private VendingMachine machine;
    private JLabel status;

    public VendingMachineGUI() {

        // ===== MACHINE =====
        machine = new VendingMachine("School");

        // ===== INVENTORY =====
        machine.getInventory().addProduct(new ProductSlot(1), new Drink("Coke", 1.50, 10));
        machine.getInventory().addProduct(new ProductSlot(2), new Drink("Pepsi", 1.50, 8));
        machine.getInventory().addProduct(new ProductSlot(3), new Drink("Sprite", 1.50, 7));
        machine.getInventory().addProduct(new ProductSlot(4), new Drink("Water", 1.00, 20));
        machine.getInventory().addProduct(new ProductSlot(5), new Drink("Energy Drink", 2.00, 6));

        machine.getInventory().addProduct(new ProductSlot(6), new Snack("Chips", 1.00, 10));
        machine.getInventory().addProduct(new ProductSlot(7), new Snack("Chocolate", 1.25, 8));
        machine.getInventory().addProduct(new ProductSlot(8), new Snack("Gum", 0.75, 25));
        machine.getInventory().addProduct(new ProductSlot(9), new Snack("Cookies", 1.75, 5));
        machine.getInventory().addProduct(new ProductSlot(10), new Snack("Candy", 0.50, 30));

        // ===== WINDOW =====
        setTitle("Vending Machine");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(12, 1));

        status = new JLabel("Insert Coins");
        add(status);

        // ===== COINS =====
        JButton coin1 = new JButton("Insert $1.00");
        JButton coin50 = new JButton("Insert $0.50");

        add(coin1);
        add(coin50);

        coin1.addActionListener(e -> {
            machine.insertCoin(new Coin(1.0));
            status.setText("Inserted $1.00");
        });

        coin50.addActionListener(e -> {
            machine.insertCoin(new Coin(0.5));
            status.setText("Inserted $0.50");
        });

        // ===== PRODUCTS (BUTTONS) =====

        addButton("Coke", 1);
        addButton("Pepsi", 2);
        addButton("Sprite", 3);
        addButton("Water", 4);
        addButton("Energy Drink", 5);
        addButton("Chips", 6);
        addButton("Chocolate", 7);
        addButton("Gum", 8);
        addButton("Cookies", 9);
        addButton("Candy", 10);

        setVisible(true);
    }

    // ===== HELPER METHOD =====
    private void addButton(String name, int id) {
        JButton btn = new JButton("Buy " + name);
        add(btn);

        btn.addActionListener(e -> {
            machine.selectItem(id);
            machine.dispense();
            status.setText("Dispensed: " + name);
        });
    }
}