package decorator;

import payment.Payment;
import payment.PaymentResult;
import store.Order;
import java.math.BigDecimal;

<<<<<<< HEAD
public class FraudDetectionDecorator extends payment.decorator.PaymentDecorator {

    public FraudDetectionDecorator(Payment decorated) {
        super(decorated);
=======
public class FraudDetectionDecorator extends PaymentDecorator {
    public FraudDetectionDecorator(Payment payment) {
        super(payment);
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
    }

    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
<<<<<<< HEAD
        boolean suspicious = amount.compareTo(new BigDecimal("1000")) > 0
                || order.getCustomer().getName().toLowerCase().contains("test");
        System.out.println("FraudDetection amount=" + amount + ", suspicious=" + suspicious);
=======
        System.out.println("gfhj");
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
        return super.pay(order, amount);
    }
}
