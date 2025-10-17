package payment;

import store.Order;
import java.math.BigDecimal;

public class CreditCardPayment implements Payment {
    private final String cardType;

    public CreditCardPayment(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public PaymentResult pay(Order order, BigDecimal amount) {
        System.out.println("Card type: " + cardType + "; Charging " + amount + " for " + order.getCustomer().getName());
        return new PaymentResult(true, amount, "Paid via " + cardType);
    }
}
