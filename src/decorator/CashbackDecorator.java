package decorator;

import payment.Payment;
import payment.PaymentResult;
import store.Order;
import java.math.BigDecimal;

public class CashbackDecorator extends payment.decorator.PaymentDecorator {
    private final int points;

    public CashbackDecorator(Payment decorated, int points) {
        super(decorated);
        this.points = points;
    }

    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        PaymentResult res = super.pay(order, amount);
        order.getCustomer().addPoints(points);
        System.out.println("Cashback: +" + points + " points → total: " + order.getCustomer().getPoints());
        return res;
    }
}
