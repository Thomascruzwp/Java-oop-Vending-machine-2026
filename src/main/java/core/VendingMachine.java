package core;

import state.*;
import hardware.Coin;

public class VendingMachine {

    private double currentBalance;
    private String location;
    private VendingState state;

    private String message = "";

    public VendingMachine(String location) {
        this.location = location;
        this.state = new ReadyState(this);
        this.currentBalance = 0;

        // ✅ FIX: use location so it is not "unused"
        setMessage("Machine ready at " + location);
    }

    // ===== GUI MESSAGE SYSTEM =====
    public void setMessage(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
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

    // ===== OPTIONAL (FOR GUI DISPLAY) =====
    public String getLocation() {
        return location;
    }
}