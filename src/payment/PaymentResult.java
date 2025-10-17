package payment;

import java.math.BigDecimal;

public class PaymentResult {
    private final boolean success;
    private final BigDecimal amount;
    private final String message;

    public PaymentResult(boolean success, BigDecimal amount, String message) {
        this.success = success;
        this.amount = amount;
        this.message = message;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }
}
