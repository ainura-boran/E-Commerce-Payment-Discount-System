package decorator;

import payment.Payment;
import payment.PaymentResult;
import store.Order;
import java.math.BigDecimal;

public class DiscountDecorator extends PaymentDecorator {
    private final BigDecimal discountPercent;

    public DiscountDecorator(Payment payment, BigDecimal discountPercent) {
        super(payment);
        this.discountPercent = discountPercent;
    }

    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        BigDecimal discounted = amount.subtract(amount.multiply(discountPercent).divide(BigDecimal.valueOf(100)));
        System.out.println("Discount Decorator: " + discountPercent + "% discount applied. New amount: " + discounted);
        return super.pay(order, discounted);
    }
}
