package app;

import facade.CheckoutFacade;
import payment.Payment;
import payment.PaymentFactory;
import payment.PaymentType;
import store.Cart;
import store.Customer;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class OrderService {
    private static final String JDBC_URL  = "jdbc:postgresql://localhost:5432/ecommerce";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASS = "postgres";
    private final Scanner sc = new Scanner(System.in);

    private Integer currentCustomerId = null;

    public void start() {
        System.out.println("--- Welcome to our E-Commerce system ---");
        System.out.println("1) Create profile");
        System.out.println("2) Choose customer by ID");
        System.out.print("Your choice: ");

        int firstChoice = readInt();
        Customer customer;
        if (firstChoice == 1) {
            customer = createCustomerProfile();
        } else if (firstChoice == 2) {
            customer = chooseCustomerById();
        } else {
            System.out.println("Unexpected choice. Exiting.");
            return;
        }

        Cart cart = createCart();
        System.out.println("Total price: " + cart.total() + " ₸");
        Payment payment = choosePayment();

        CheckoutFacade.Options opts = new CheckoutFacade.Options();
        opts.discountPercents = List.of(new BigDecimal("10"));
        opts.enableCashback = true;
        opts.cashbackPoints = 30;
        opts.enableFraudDetection = true;

        new CheckoutFacade().processOrder(cart, customer, payment, opts);

        if (opts.enableCashback && currentCustomerId != null) {
            addPoints(currentCustomerId, opts.cashbackPoints);
        }
    }

    private Customer createCustomerProfile() {
        System.out.println("--- Creating customer's profile ---");
        System.out.print("Enter your full name: ");
        String name = sc.nextLine();
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        System.out.print("Enter your address: ");
        String address = sc.nextLine();

        Customer customer = new Customer(name, email, address, 0);
        int id = saveCustomer(customer);
        currentCustomerId = id;
        System.out.println("Profile is created. Your customer ID: " + id);
        return customer;
    }

    private Customer chooseCustomerById() {
        while (true) {
            System.out.print("Enter existing customer ID: ");
            int id = readInt();
            Customer customer = findCustomerById(id);
            if (customer != null) {
                currentCustomerId = id;
                System.out.println("Customer found: " + customer);
                return customer;
            } else {
                System.out.println("Customer with ID " + id + " not found. Try again.");
            }
        }
    }

    private int saveCustomer(Customer customer) {
        String sql = "INSERT INTO customers (name, email, address, points) VALUES (?,?,?,?) RETURNING id";
        try (Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getAddress());
            ps.setInt(4, customer.getPoints());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
                throw new SQLException("INSERT did not return id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("saveCustomer failed: " + e.getMessage(), e);
        }
    }

    private Customer findCustomerById(int id) {
        String sql = "SELECT name, email, address, points FROM customers WHERE id = ?";
        try (Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    int points = rs.getInt("points");
                    return new Customer(name, email, address, points);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("findCustomerById failed: " + e.getMessage(), e);
        }
    }

    private Cart createCart() {
        Cart cart = new Cart();
        System.out.println("--- Adding items to cart ---");
        while (true) {
            System.out.print("Enter item's price (press Enter to finish): ");
            String input = sc.nextLine();
            if (input.isEmpty()) break;
            try {
                BigDecimal price = new BigDecimal(input);
                cart.add(price);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again.");
            }
        }
        return cart;
    }

    private Payment choosePayment() {
        System.out.println("Select the type of payment:");
        System.out.println("1) Visa");
        System.out.println("2) MasterCard");
        System.out.println("3) PayPal");
        System.out.print("Your choice: ");
        int choice = readInt();

        PaymentType type = switch (choice) {
            case 1 -> PaymentType.VISA;
            case 2 -> PaymentType.MASTERCARD;
            case 3 -> PaymentType.PAYPAL;
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
        return PaymentFactory.create(type);
    }

    private int readInt() {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid integer: ");
            }
        }
    }

    private void addPoints(int customerId, int delta) {
        String sql = "UPDATE customers SET points = points + ? WHERE id = ?";
        try (Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, delta);
            ps.setInt(2, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("addPoints failed: " + e.getMessage(), e);
        }
    }
}
