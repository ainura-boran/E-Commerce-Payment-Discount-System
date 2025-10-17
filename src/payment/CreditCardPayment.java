package payment;

import store.Order;
import java.math.BigDecimal;

public class CreditCardPayment implements Payment {
    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        System.out.println("Credit card payment processing: " + amount + " for " + order.getCustomer().getName());
        return new PaymentResult(true, amount, "Paid via Credit Card");
    }
}
