package app;

import repository.CustomerRepository;
import repository.JdbcCustomerRepository;

public class Main {
    public static void main(String[] args) {
        DataBase db = new DataBase(
                "jdbc:postgresql://localhost:5432/ecommerce",
                "postgres",
                "postgres"
        );
        CustomerRepository customerRepo = new JdbcCustomerRepository(db);
        OrderService orderService = new OrderService(customerRepo);
        Controller ui = new Controller(orderService, new java.util.Scanner(System.in));
        ui.start();
    }
}
