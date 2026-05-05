package core;

import java.util.HashMap;
import java.util.Map;
import product.*;

public class Inventory {

    private Map<ProductSlot, Product> products = new HashMap<>();

    public void addProduct(ProductSlot slot, Product product) {
        products.put(slot, product);
    }

    public Product getProduct(ProductSlot slot) {
        return products.get(slot);
    }

    public Map<ProductSlot, Product> getProducts() {
        return products;
    }
}