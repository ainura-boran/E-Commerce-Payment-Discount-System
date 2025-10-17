package decorator;

import payment.Payment;
import payment.PaymentResult;
import store.Order;
import java.math.BigDecimal;

public abstract class PaymentDecorator implements Payment {
    protected final Payment decoratedPayment;

    protected PaymentDecorator(Payment decoratedPayment) {
        this.decoratedPayment = decoratedPayment;
    }

    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        return decoratedPayment.pay(order, amount);
    }
}
