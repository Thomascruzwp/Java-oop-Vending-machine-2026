package core;

import state.*;
import hardware.Coin;

public class VendingMachine {

    private double currentBalance;
    private String location;
    private VendingState state;

    private Inventory inventory;
    private String message = "";

    public VendingMachine(String location) {
        this.location = location;
        this.state = new ReadyState(this);
        this.currentBalance = 0;

        this.inventory = new Inventory();

        setMessage("Machine ready at " + location);
    }

    // ================= MESSAGE =================
    public void setMessage(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    // ================= INVENTORY =================
    public Inventory getInventory() {
        return inventory;
    }

    // ================= ACTIONS =================
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

    // ================= BALANCE =================
    public void addBalance(double amount) {
        currentBalance += amount;
    }

    public double getBalance() {
        return currentBalance;
    }

    // ================= RESET (FIXED) =================
    public void reset() {
        currentBalance = 0;

        // ❌ REMOVED MESSAGE OVERWRITE (IMPORTANT FIX)
        // previously: setMessage("Machine reset at " + location);

        setState(new ReadyState(this));
    }

    // ================= STATE =================
    public void setState(VendingState state) {
        this.state = state;
    }

    // ================= LOCATION =================
    public String getLocation() {
        return location;
    }
}