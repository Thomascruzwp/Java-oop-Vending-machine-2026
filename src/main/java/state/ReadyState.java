package state;

import core.VendingMachine;
import hardware.Coin;

public class ReadyState implements VendingState {

    private VendingMachine machine;

    public ReadyState(VendingMachine machine) {
        this.machine = machine;
    }

    public void select(int item) {
        System.out.println("Insert coin first");
    }

    public void insert(Coin coin) {
        machine.addBalance(coin.getValue());
        System.out.println("Inserted: " + coin.getValue());
    }

    public void dispense() {
        System.out.println("Not ready yet");
    }

    public void cancel() {
        System.out.println("Nothing to cancel");
    }
}