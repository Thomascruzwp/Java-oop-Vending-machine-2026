package state;

import core.VendingMachine;
import hardware.Coin;

public class ReadyState implements VendingState {

    private VendingMachine machine;

    public ReadyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insert(Coin coin) {
        machine.addBalance(coin.getValue());
        machine.setMessage("Inserted: " + coin.getValue());
    }

    @Override
    public void select(int item) {
        machine.setMessage("Insert coin first");
    }

    @Override
    public void dispense() {
        machine.setMessage("Cannot dispense yet");
    }

    @Override
    public void cancel() {
        machine.setMessage("Nothing to cancel");
    }
}