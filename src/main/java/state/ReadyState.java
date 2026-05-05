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
        machine.setMessage("Inserted: " + coin.getValue() + 
                            " | Balance: " + machine.getBalance());
    }

    @Override
    public void select(int item) {

        if (machine.getBalance() <= 0) {
            machine.setMessage("Insert coins first");
            return;
        }

        machine.setMessage("Item selected: " + item);

        // IMPORTANT: move to payment state
        machine.setState(new ProcessingPaymentState(machine));
    }

    @Override
    public void dispense() {
        machine.setMessage("Select item first");
    }

    @Override
    public void cancel() {
        machine.setMessage("Nothing to cancel");
    }
}