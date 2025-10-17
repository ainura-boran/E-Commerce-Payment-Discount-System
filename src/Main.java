import facade.CheckoutFacade;
import payment.*;
import store.*;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer(
                "Ainur", "boranova2007@mail.ru", "Aktau, Kazakhstan", 120);

        Cart cart = new Cart();
        cart.add(new BigDecimal("200"));
        cart.add(new BigDecimal("150"));
        cart.add(new BigDecimal("50"));

        System.out.println("Select the type of payment:");
        System.out.println("1) Visa");
        System.out.println("2) MasterCard");
        System.out.println("3) PayPal");
        System.out.print("Your choice: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        PaymentType type = switch (choice) {
            case 1 -> PaymentType.VISA;
            case 2 -> PaymentType.MASTERCARD;
            case 3 -> PaymentType.PAYPAL;
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
        Payment basePayment = PaymentFactory.create(type);

        CheckoutFacade.Options opts = new CheckoutFacade.Options();
        opts.discountPercents = List.of(new BigDecimal("10"));
        opts.enableCashback = true;
        opts.cashbackPoints = 30;
        opts.enableFraudDetection = true;

        CheckoutFacade facade = new CheckoutFacade();
        facade.processOrder(cart, customer, basePayment, opts);
    }
}
