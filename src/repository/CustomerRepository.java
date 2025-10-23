package repository;

import store.Customer;
import java.util.Optional;

public interface CustomerRepository {
    int save(Customer customer);
    Optional<Customer> findById(int id);
    void addPoints(int customerId, int delta);
}
