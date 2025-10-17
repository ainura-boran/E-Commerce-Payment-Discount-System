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
    }
}
