package state;

import core.VendingMachine;
import hardware.Coin;

public class ReadyState implements VendingState {

    private VendingMachine machine;

    public ReadyState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insert(Coin coin) {
        machine.addBalance(coin.getValue());
        System.out.println("Coin inserted: " + coin.getValue());
    }

    public void select(int item) {
        System.out.println("Select after payment.");
    }

    public void dispense() {}

    public void cancel() {
        System.out.println("Nothing to cancel.");
    }
}