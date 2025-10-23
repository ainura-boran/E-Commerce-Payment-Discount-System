package decorator;

import payment.Payment;
import payment.PaymentResult;
import store.Order;

import java.math.BigDecimal;

public class FraudDetectionDecorator extends PaymentDecorator {
    public FraudDetectionDecorator(Payment decorated) {
        super(decorated);
    }

    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        boolean suspicious = amount.compareTo(new BigDecimal("1000")) > 0 || order.getCustomer().getName().toLowerCase().contains("test");
        System.out.println("Fraud detection amount = " + amount + ", suspicious = " + suspicious);
        return super.pay(order, amount);
    }
}
