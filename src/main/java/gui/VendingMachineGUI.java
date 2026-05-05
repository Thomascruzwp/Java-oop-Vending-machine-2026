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

        // ITEMS
        machine.getInventory().addProduct(new ProductSlot(1), new Drink("Coke", 1.50, 10));
        machine.getInventory().addProduct(new ProductSlot(2), new Drink("Pepsi", 1.50, 8));
        machine.getInventory().addProduct(new ProductSlot(3), new Drink("Water", 1.00, 15));
        machine.getInventory().addProduct(new ProductSlot(4), new Snack("Chips", 1.00, 10));

        // WINDOW
        setTitle("Vending Machine");
        setSize(400, 450);
        setLayout(new GridLayout(0, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        status = new JLabel("Insert Coins");
        balance = new JLabel("Balance: $0.00");

        add(status);
        add(balance);

        // COINS
        JButton coin1 = new JButton("$1 Coin");
        JButton coin50 = new JButton("$0.50 Coin");

        add(coin1);
        add(coin50);

        coin1.addActionListener(e -> {
            machine.insertCoin(new Coin(1.0));
            updateUI();
        });

        coin50.addActionListener(e -> {
            machine.insertCoin(new Coin(0.5));
            updateUI();
        });

        // ITEMS
        addButton("Coke", 1);
        addButton("Pepsi", 2);
        addButton("Water", 3);
        addButton("Chips", 4);

        setVisible(true);
    }

    private void addButton(String name, int id) {
        JButton btn = new JButton("Buy " + name);

        btn.addActionListener(e -> {
            machine.selectItem(id);
            machine.dispense();
            updateUI();
        });

        add(btn);
    }

    private void updateUI() {
        status.setText(machine.getMessage());
        balance.setText("Balance: $" + machine.getBalance());
    }
}