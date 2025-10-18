package store;

import java.math.BigDecimal;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<BigDecimal> items = new ArrayList<>();
    public void add(BigDecimal price) { items.add(price); }
    public BigDecimal total() { return items.stream().reduce(BigDecimal.ZERO, BigDecimal::add); }
}

=======
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
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
