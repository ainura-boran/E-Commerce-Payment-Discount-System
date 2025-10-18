package payment;

import store.Order;
import java.math.BigDecimal;

public class PayPalPayment implements Payment {
    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
<<<<<<< HEAD
        System.out.println("PayPal Transferring " + amount + " for " + order.getCustomer().getName());
=======
        System.out.println("PayPal Payment sending: " + amount + " for " + order.getCustomer().getName());
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
        return new PaymentResult(true, amount, "Paid via PayPal");
    }
}
