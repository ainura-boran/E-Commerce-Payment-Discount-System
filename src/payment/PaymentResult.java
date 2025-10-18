package payment;

import java.math.BigDecimal;

public class PaymentResult {
    private final boolean success;
<<<<<<< HEAD
    private final BigDecimal chargedAmount;
    private final String message;

    public PaymentResult(boolean success, BigDecimal chargedAmount, String message) {
        this.success = success;
        this.chargedAmount = chargedAmount;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public BigDecimal getChargedAmount() { return chargedAmount; }
    public String getMessage() { return message; }
=======
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
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
}
