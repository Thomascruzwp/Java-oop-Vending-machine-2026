package state;

import core.VendingMachine;

public class ReadyState implements VendingState {

    private VendingMachine machine;

    public ReadyState(VendingMachine machine) {
        this.machine = machine;
    }

    public void select(int item) {
        System.out.println("Insert coin first");
    }

    public void insert() {
        machine.setState(new ProcessingPaymentState(machine));
    }

    public void dispense() {}

    public void cancel() {
        machine.reset();
    }
}