package decorator;

import payment.Payment;
import payment.PaymentResult;
import store.Order;
import java.math.BigDecimal;

<<<<<<< HEAD
public class CashbackDecorator extends payment.decorator.PaymentDecorator {
    private final int points;

    public CashbackDecorator(Payment decorated, int points) {
        super(decorated);
        this.points = points;
=======
public class CashbackDecorator extends PaymentDecorator {
    private final int cashbackPoints;

    public CashbackDecorator(Payment payment, int cashbackPoints) {
        super(payment);
        this.cashbackPoints = cashbackPoints;
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
    }

    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
<<<<<<< HEAD
        PaymentResult res = super.pay(order, amount);
        order.getCustomer().addPoints(points);
        System.out.println("Cashback: +" + points + " points → total: " + order.getCustomer().getPoints());
        return res;
=======
        PaymentResult result = super.pay(order, amount);
        System.out.println("Cashback Decorator: " + cashbackPoints + " points added to " + order.getCustomer().getName());
        return result;
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
    }
}
