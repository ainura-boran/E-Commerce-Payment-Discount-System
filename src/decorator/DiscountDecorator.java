package decorator;

import payment.Payment;
import payment.PaymentResult;
import store.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountDecorator extends PaymentDecorator {
    private final BigDecimal percent;

    public DiscountDecorator(Payment decorated, BigDecimal percent) {
        super(decorated);
        this.percent = percent;
    }

    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        BigDecimal discounted = amount.subtract(
                amount.multiply(percent).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
        );
        System.out.println("Discount: " + percent + "% → " + discounted);
        return super.pay(order, discounted);
    }
}
