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
        System.out.println("Already dispensing");
    }

    @Override
    public void insert(Coin coin) {
        System.out.println("Cannot insert now");
    }

    @Override
    public void dispense() {
        System.out.println("Finished dispensing");

        machine.reset();
        machine.setState(new ReadyState(machine));
    }

    @Override
    public void cancel() {
        System.out.println("Cannot cancel");
    }
}