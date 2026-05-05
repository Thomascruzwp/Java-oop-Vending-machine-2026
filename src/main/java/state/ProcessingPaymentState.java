package state;

import core.VendingMachine;
import hardware.Coin;

public class ProcessingPaymentState implements VendingState {

    private VendingMachine machine;

    public ProcessingPaymentState(VendingMachine machine) {
        this.machine = machine;
    }

    public void select(int item) {
        System.out.println("Processing payment...");
    }

    public void insert(Coin coin) {
        machine.addBalance(coin.getValue());
    }

    public void dispense() {
        System.out.println("Dispensing...");
        machine.reset();
        machine.setState(new ReadyState(machine));
    }

    public void cancel() {
        System.out.println("Cancelled");
        machine.reset();
        machine.setState(new ReadyState(machine));
    }
}