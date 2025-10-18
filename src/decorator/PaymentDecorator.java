<<<<<<< HEAD
package payment.decorator;
=======
package decorator;
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728

import payment.Payment;
import payment.PaymentResult;
import store.Order;
import java.math.BigDecimal;

public abstract class PaymentDecorator implements Payment {
<<<<<<< HEAD
    protected final Payment decorated;

    protected PaymentDecorator(Payment decorated) {
        this.decorated = decorated;
=======
    protected final Payment decoratedPayment;

    protected PaymentDecorator(Payment decoratedPayment) {
        this.decoratedPayment = decoratedPayment;
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
    }

    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
<<<<<<< HEAD
        return decorated.pay(order, amount);
=======
        return decoratedPayment.pay(order, amount);
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
    }
}
