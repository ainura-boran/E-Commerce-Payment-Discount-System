package payment;

import store.Order;
import java.math.BigDecimal;

public class CreditCardPayment implements Payment {
<<<<<<< HEAD
    private final String cardType;

    public CreditCardPayment(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        System.out.println("Card type: " + cardType + "; Charging " + amount + " for " + order.getCustomer().getName());
        return new PaymentResult(true, amount, "Paid via " + cardType);
=======
    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        System.out.println("Credit card payment processing: " + amount + " for " + order.getCustomer().getName());
        return new PaymentResult(true, amount, "Paid via Credit Card");
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
    }
}
