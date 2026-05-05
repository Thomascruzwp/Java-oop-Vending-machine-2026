package gui;

import core.*;

public class VendingMachineGUI {

    private VendingMachine machine;

    public VendingMachineGUI() {
        machine = new VendingMachine("School Lobby");

        System.out.println("Vending Machine GUI Started");
        System.out.println("STATE: READY");
    }
}