package store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<BigDecimal> items = new ArrayList<>();

    public void add(BigDecimal price) {
        items.add(price);
    }

    public BigDecimal total() {
        return items.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}