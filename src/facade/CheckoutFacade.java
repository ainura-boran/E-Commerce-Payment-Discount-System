package facade;

import payment.*;
import decorator.*;
import store.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CheckoutFacade {

    public static final class Options {
        public List<BigDecimal> discountPercents;
        public boolean enableFraudDetection;
        public boolean enableCashback;
        public int cashbackPoints = 0;
    }

    public PaymentResult processOrder(Cart cart, Customer customer, Payment base, Options opts) {
        Order order = new Order(customer, cart.total());
        Payment pipeline = base;

        if (opts != null && opts.discountPercents != null) {
            for (BigDecimal p : opts.discountPercents) {
                pipeline = new DiscountDecorator(pipeline, p);
            }
        }
        if (opts != null && opts.enableCashback) {
            pipeline = new CashbackDecorator(pipeline, opts.cashbackPoints);
        }
        if (opts != null && opts.enableFraudDetection) {
            pipeline = new FraudDetectionDecorator(pipeline);
        }

        System.out.println("--- Checkout for " + customer.getName() + " | Total: " + order.getAmount() + " ---");
        PaymentResult result = pipeline.pay(order, order.getAmount());

        System.out.println("""
                ----- RECEIPT -----
                Customer: %s
                Email:    %s
                Address:  %s
                Charged:  %s
                Status:   %s
                Date:     %s
                -------------------
                """.formatted(
                customer.getName(),
                customer.getEmail(),
                customer.getAddress(),
                result.getChargedAmount(),
                result.getMessage(),
                LocalDateTime.now()
        ));
        return result;
    }
}
