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
        machine.getInventory().addProduct(new ProductSlot(2), new Drink("Pepsi", 1.50, 8));
        machine.getInventory().addProduct(new ProductSlot(3), new Drink("Water", 1.00, 15));
        machine.getInventory().addProduct(new ProductSlot(4), new Snack("Chips", 1.00, 10));
        machine.getInventory().addProduct(new ProductSlot(5), new Snack("Chocolate", 1.75, 6));
        machine.getInventory().addProduct(new ProductSlot(6), new Snack("Candy", 0.75, 20));
        machine.getInventory().addProduct(new ProductSlot(7), new Drink("Juice", 1.25, 12));
        machine.getInventory().addProduct(new ProductSlot(8), new Snack("Gum", 0.50, 30));

        // ================= WINDOW =================
        setTitle("Vending Machine");
        setSize(400, 600);
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
        addButton("Pepsi", 2, 1.50);
        addButton("Water", 3, 1.00);
        addButton("Chips", 4, 1.00);
        addButton("Chocolate", 5, 1.75);
        addButton("Candy", 6, 0.75);
        addButton("Juice", 7, 1.25);
        addButton("Gum", 8, 0.50);

        setVisible(true);
    }

    // ================= BUTTON LOGIC =================
    private void addButton(String name, int id, double price) {

        JButton btn = new JButton(name + " ($" + price + ")");

        btn.addActionListener(e -> {

            // ❌ NOT ENOUGH MONEY
            if (machine.getBalance() < price) {
                machine.setMessage("Not enough money for " + name);
                updateUI();
                return;
            }

            // ✔ SELECT ITEM
            machine.selectItem(id);

            // ✔ SHOW DISPENSING MESSAGE (THIS STAYS NOW)
            machine.setMessage("Dispensing " + name + "...");

            // ✔ DEDUCT MONEY
            machine.addBalance(-price);

            // ✔ DISPENSE
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