package facade;

import payment.*;
import decorator.*;
import store.*;
import java.math.BigDecimal;

public class CheckoutFacade {
    public PaymentResult processOrder(Cart cart, Customer customer, Payment basePayment, boolean applyDiscount, BigDecimal discountPercent, boolean cashback, int cashbackPoints, boolean fraudDetection) {
        Payment payment = basePayment;

        if (applyDiscount)
            payment = new DiscountDecorator(payment, discountPercent);

        if (cashback)
            payment = new CashbackDecorator(payment, cashbackPoints);

        if (fraudDetection)
            payment = new FraudDetectionDecorator(payment);

        Order order = new Order(customer, cart.total());
        System.out.println("Processing Order for " + customer.getName() + " ===");

        PaymentResult result = payment.pay(order, order.getAmount());
        System.out.println("Payment Complete: " + result.getMessage() + " ===");

        return result;
    }
}
