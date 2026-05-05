package state;

import hardware.Coin;

public interface VendingState {

    void select(int item);
    void insert(Coin coin);
    void dispense();
    void cancel();
}