package core;

import state.*;
import hardware.*;
import product.*;

public class VendingMachine {

    private double currentBalance;
    private String location;

    private Inventory inventory;
    private CoinAcceptor coinAcceptor;

    private VendingState state;

    public VendingMachine(String location) {
        this.location = location;
        this.inventory = new Inventory();
        this.coinAcceptor = new CoinAcceptor();

        this.state = new ReadyState(this);
        this.currentBalance = 0;
    }

    // UML METHODS
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

    // STATE CONTROL
    public void setState(VendingState state) {
        this.state = state;
    }

    // BALANCE
    public void addBalance(double amount) {
        currentBalance += amount;
    }

    public double getBalance() {
        return currentBalance;
    }

    // GETTERS
    public Inventory getInventory() {
        return inventory;
    }

    public CoinAcceptor getCoinAcceptor() {
        return coinAcceptor;
    }
}