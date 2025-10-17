package payment;

import store.Order;
import java.math.BigDecimal;

public interface Payment {
    PaymentResult pay(Order order, BigDecimal amount);
}