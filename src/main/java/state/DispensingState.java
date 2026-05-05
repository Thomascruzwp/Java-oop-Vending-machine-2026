package state;

import core.VendingMachine;

public class DispensingState implements VendingState {

    private VendingMachine machine;

    public DispensingState(VendingMachine machine) {
        this.machine = machine;
    }

    public void select(int item) {}

    public void insert() {}

    public void dispense() {
        machine.reset();
        machine.setState(new ReadyState(machine));
    }

    public void cancel() {
        machine.reset();
        machine.setState(new ReadyState(machine));
    }
}