package core;

import state.*;
import hardware.Coin;

public class VendingMachine {

    private double currentBalance;
    private String location;

    private Inventory inventory;
    private VendingState state;

    public VendingMachine(String location) {
        this.location = location;
        this.inventory = new Inventory();
        this.state = new ReadyState(this);
        this.currentBalance = 0;
    }

    public void insertCoin(Coin coin) {
        state.insert(coin);
    }

    public void selectItem(int id) {
        state.select(id);
    }

    public void dispense() {
        state.dispense();
    }

    public void cancel() {
        state.cancel();
    }

    public void setState(VendingState state) {
        this.state = state;
    }

    public void addBalance(double amount) {
        currentBalance += amount;
    }

    public double getBalance() {
        return currentBalance;
    }

    // 🔥 FIX FOR YOUR ERROR
    public void reset() {
        currentBalance = 0;
        System.out.println("Machine reset");
    }

    public Inventory getInventory() {
        return inventory;
    }
}