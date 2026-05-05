package core;

public class ProductSlot {
    private int id;

    public ProductSlot(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof ProductSlot) &&
                ((ProductSlot) o).id == id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}