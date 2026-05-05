package core;

import state.*;
import hardware.Coin;
import core.Inventory;   // ✅ REQUIRED

public class VendingMachine {

    private double currentBalance;
    private String location;
    private VendingState state;

    private Inventory inventory;   // ✅ FIXED
    private String message = "";

    public VendingMachine(String location) {
        this.location = location;
        this.state = new ReadyState(this);
        this.currentBalance = 0;

        this.inventory = new Inventory(); // ✅ FIXED

        setMessage("Machine ready at " + location);
    }

    // ===== GUI MESSAGE SYSTEM =====
    public void setMessage(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    // ===== INVENTORY ACCESS (FIX YOUR ERROR) =====
    public Inventory getInventory() {
        return inventory;
    }

    // ===== CORE ACTIONS =====
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

    // ===== BALANCE =====
    public void addBalance(double amount) {
        currentBalance += amount;
    }

    public double getBalance() {
        return currentBalance;
    }

    public void reset() {
        currentBalance = 0;
        setMessage("Machine reset at " + location);
    }

    public void setState(VendingState state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }
}