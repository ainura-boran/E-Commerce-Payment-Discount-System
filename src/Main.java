<<<<<<< HEAD
import facade.CheckoutFacade;
import payment.*;
import store.*;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Creating customer's profile ---");
        System.out.print("Enter your full name: ");
        String name = sc.nextLine();

        System.out.print("Enter your email: ");
        String email = sc.nextLine();

        System.out.print("Enter your address: ");
        String address = sc.nextLine();

        Customer customer = new Customer(name, email, address, 0);
        System.out.println("Profile is created");

        Cart cart = new Cart();
        System.out.println("--- Adding items to cart ---");
        while (true) {
            System.out.print("Enter item's price (press Enter to finish): ");
            String input = sc.nextLine();

            if (input.equals("")) break;
            try {
                BigDecimal price = new BigDecimal(input);
                cart.add(price);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again.");
            }
        }

        System.out.println("Total price: " + cart.total() + " ₸");
        System.out.println("Select the type of payment:");
        System.out.println("1) Visa");
        System.out.println("2) MasterCard");
        System.out.println("3) PayPal");
        System.out.print("Your choice: ");

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
=======
import payment.*;
import store.*;
import facade.CheckoutFacade;
import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CheckoutFacade facade = new CheckoutFacade();

        Cart cart = new Cart(Arrays.asList(BigDecimal.valueOf(100), BigDecimal.valueOf(50)));
        Customer alice = new Customer("Ainur");
        Customer bob = new Customer("Dinara");

        facade.processOrder(cart, alice, new CreditCardPayment(), true, BigDecimal.valueOf(10), false, 0, true);

        facade.processOrder(cart, bob, new PayPalPayment(), false, BigDecimal.ZERO, true, 50, false);
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
    }
}
