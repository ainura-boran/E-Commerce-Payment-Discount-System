package decorator;

import payment.Payment;
import payment.PaymentResult;
import store.Order;
import java.math.BigDecimal;

public class FraudDetectionDecorator extends PaymentDecorator {
    public FraudDetectionDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        System.out.println("gfhj");
        return super.pay(order, amount);
    }
}
