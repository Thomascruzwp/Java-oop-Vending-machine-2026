package state;

import core.VendingMachine;
import hardware.Coin;

public class DispensingState implements VendingState {

    private VendingMachine machine;

    public DispensingState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void select(int item) {
        machine.setMessage("Already dispensing");
    }

    @Override
    public void insert(Coin coin) {
        machine.setMessage("Cannot insert now");
    }

    @Override
    public void dispense() {
        machine.setMessage("Dispensing item...");
        machine.reset();
        machine.setState(new ReadyState(machine));
    }

    @Override
    public void cancel() {
        machine.setMessage("Cannot cancel");
    }
}