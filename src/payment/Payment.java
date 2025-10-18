package payment;

import store.Order;
import java.math.BigDecimal;

public interface Payment {
    PaymentResult pay(Order order, BigDecimal amount);
<<<<<<< HEAD
}
=======
}
>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
