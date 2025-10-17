package decorator;

import payment.Payment;
import payment.PaymentResult;
import store.Order;
import java.math.BigDecimal;

public class CashbackDecorator extends PaymentDecorator {
    private final int cashbackPoints;

    public CashbackDecorator(Payment payment, int cashbackPoints) {
        super(payment);
        this.cashbackPoints = cashbackPoints;
    }

    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        PaymentResult result = super.pay(order, amount);
        System.out.println("Cashback Decorator: " + cashbackPoints + " points added to " + order.getCustomer().getName());
        return result;
    }
}
