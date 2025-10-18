package store;

import java.math.BigDecimal;

public class Order {
    private final Customer customer;
    private final BigDecimal amount;

    public Order(Customer customer, BigDecimal amount) {
        this.customer = customer;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Customer getCustomer() {
        return customer;
    }
}

