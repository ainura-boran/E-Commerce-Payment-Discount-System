package payment;

import store.Order;
import java.math.BigDecimal;

public class PayPalPayment implements Payment {
    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        System.out.println("PayPal Transferring " + amount + " for " + order.getCustomer().getName());
        return new PaymentResult(true, amount, "Paid via PayPal");
    }
}
