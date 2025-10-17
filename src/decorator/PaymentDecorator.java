package payment.decorator;

import payment.Payment;
import payment.PaymentResult;
import store.Order;
import java.math.BigDecimal;

public abstract class PaymentDecorator implements Payment {
    protected final Payment decorated;

    protected PaymentDecorator(Payment decorated) {
        this.decorated = decorated;
    }

    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        return decorated.pay(order, amount);
    }
}
