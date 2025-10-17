package payment;

import java.math.BigDecimal;

public class PaymentResult {
    private final boolean success;
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
}
