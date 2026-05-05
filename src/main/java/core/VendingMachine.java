package core;

import state.VendingState;
import state.ReadyState;
import hardware.Coin;
import hardware.CoinAcceptor;

public class VendingMachine {

    private double currentBalance;
    private String location;

    private Inventory inventory;              // ✔ UML FIX
    private CoinAcceptor coinAcceptor;        // ✔ Aggregation
    private VendingState state;

    public VendingMachine(String location, Inventory inventory) {
        this.location = location;
        this.inventory = inventory;
        this.coinAcceptor = new CoinAcceptor();
        this.state = new ReadyState(this);
    }

    // UML methods
    public void selectItem(int id) {
        state.select(id);
    }

    public void insertCoin(Coin coin) {
        if (coinAcceptor.accept(coin)) {
            state.insert();
            currentBalance += coin.getValue();
        }
    }

    // getters/setters
    public double getBalance() {
        return currentBalance;
    }

    public void deduct(double amount) {
        currentBalance -= amount;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setState(VendingState state) {
        this.state = state;
    }

    public void reset() {
        currentBalance = 0;
    }
}