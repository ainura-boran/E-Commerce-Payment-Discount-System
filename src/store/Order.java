package store;

import java.math.BigDecimal;

public class Order {
    private final Customer customer;
    private final BigDecimal amount;

    public Order(Customer customer, BigDecimal amount) {
        this.customer = customer;
        this.amount = amount;
    }

<<<<<<< HEAD
    public Customer getCustomer() { return customer; }
    public BigDecimal getAmount() { return amount; }
=======
    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal getAmount() {
        return amount;
    }
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
}

