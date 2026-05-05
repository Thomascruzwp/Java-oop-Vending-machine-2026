package gui;

import core.*;
import product.*;
import hardware.Coin;

public class VendingMachineGUI {

    public VendingMachineGUI() {

        VendingMachine machine = new VendingMachine("School");

        machine.getInventory().addProduct(new ProductSlot(1),
            new Drink("Coke", 1.50, 10));

        machine.getInventory().addProduct(new ProductSlot(2),
            new Snack("Chips", 1.00, 5));

        System.out.println("=== VENDING MACHINE STARTED ===");

        machine.insertCoin(new Coin(1.00));
        machine.insertCoin(new Coin(1.00));

        machine.selectItem(1);
        machine.dispense();

        machine.cancel();
    }
}