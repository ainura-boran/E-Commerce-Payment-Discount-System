package payment;

public class PaymentFactory {
    public static Payment create(PaymentType type) {
        return switch (type) {
            case VISA -> new CreditCardPayment("Visa");
            case MASTERCARD -> new CreditCardPayment("MasterCard");
            case PAYPAL -> new PayPalPayment();
        };
    }
}
