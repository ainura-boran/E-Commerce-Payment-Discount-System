package store;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
    private final List<BigDecimal> items;

    public Cart(List<BigDecimal> items) {
        this.items = items;
    }

    public BigDecimal total() {
        return items.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
