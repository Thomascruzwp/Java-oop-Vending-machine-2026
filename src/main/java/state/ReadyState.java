package state;

import core.VendingMachine;
import hardware.Coin;

public class ReadyState implements VendingState {

    private VendingMachine machine;

    public ReadyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void select(int item) {
        System.out.println("Insert coin first");
    }

    @Override
    public void insert(Coin coin) {
        machine.addBalance(coin.getValue());
        System.out.println("Inserted: " + coin.getValue());
    }

    @Override
    public void dispense() {
        System.out.println("Cannot dispense yet");
    }

    @Override
    public void cancel() {
        System.out.println("Nothing to cancel");
    }
}