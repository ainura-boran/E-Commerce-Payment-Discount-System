package decorator;

import payment.Payment;
import payment.PaymentResult;
import store.Order;
import java.math.BigDecimal;
<<<<<<< HEAD
import java.math.RoundingMode;

public class DiscountDecorator extends payment.decorator.PaymentDecorator {
    private final BigDecimal percent;

    public DiscountDecorator(Payment decorated, BigDecimal percent) {
        super(decorated);
        this.percent = percent;
=======

public class DiscountDecorator extends PaymentDecorator {
    private final BigDecimal discountPercent;

    public DiscountDecorator(Payment payment, BigDecimal discountPercent) {
        super(payment);
        this.discountPercent = discountPercent;
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
    }

    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
<<<<<<< HEAD
        BigDecimal discounted = amount.subtract(
                amount.multiply(percent).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
        );
        System.out.println("Discount: " + percent + "% → " + discounted);
=======
        BigDecimal discounted = amount.subtract(amount.multiply(discountPercent).divide(BigDecimal.valueOf(100)));
        System.out.println("Discount Decorator: " + discountPercent + "% discount applied. New amount: " + discounted);
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
        return super.pay(order, discounted);
    }
}
