package state;

import core.VendingMachine;

public class ProcessingPaymentState implements VendingState {

    private VendingMachine machine;

    public ProcessingPaymentState(VendingMachine machine) {
        this.machine = machine;
    }

    public void select(int item) {
        machine.setState(new DispensingState(machine));
    }

    public void insert() {}

    public void dispense() {}

    public void cancel() {
        machine.reset();
        machine.setState(new ReadyState(machine));
    }
}