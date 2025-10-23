package app;

import facade.CheckoutFacade;
import payment.Payment;
import payment.PaymentFactory;
import payment.PaymentType;
import store.Cart;
import store.Customer;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import repository.CustomerRepository;

public class OrderService {
    private final CustomerRepository customers;

    public OrderService(CustomerRepository customers) {
        this.customers = customers;
    }

    public int createCustomerProfile(Customer customer) {
        return customers.save(customer);
    }

    public Optional<CustomerWithId> getCustomerById(int id) {
        return customers.findById(id).map(c -> new CustomerWithId(id, c));
    }

    public Payment choosePayment(int choice) {
        PaymentType type = switch (choice) {
            case 1 -> PaymentType.VISA;
            case 2 -> PaymentType.MASTERCARD;
            case 3 -> PaymentType.PAYPAL;
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
        return PaymentFactory.create(type);
    }

    public void checkout(Cart cart, Customer customer, Payment payment, int currentCustomerId) {
        CheckoutFacade.Options opts = new CheckoutFacade.Options();
        opts.discountPercents = List.of(new BigDecimal("10"));
        opts.enableCashback = true;
        opts.cashbackPoints = 30;
        opts.enableFraudDetection = true;

        new CheckoutFacade().processOrder(cart, customer, payment, opts);

        if (opts.enableCashback) {
            customers.addPoints(currentCustomerId, opts.cashbackPoints);
        }
    }

    public record CustomerWithId(int id, Customer data) {}
}
