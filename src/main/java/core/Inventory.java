package core;

import product.Product;
import java.util.Map;

public class Inventory {

    private Map<ProductSlot, Product> products;

    public Inventory(Map<ProductSlot, Product> products) {
        this.products = products;
    }

    public Product getProduct(ProductSlot slot) {
        return products.get(slot);
    }
}