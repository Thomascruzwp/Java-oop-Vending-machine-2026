package state;

public interface VendingState {
    void select(int item);
    void insert();
    void dispense();
    void cancel();
}