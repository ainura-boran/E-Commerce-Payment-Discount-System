package store;

public class Customer {
    private final String name;
    private final String email;
    private final String address;
    private int points;

    public Customer(String name, String email, String address, int points) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.points = points;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }

    public int getPoints() { return points; }
    public void addPoints(int p) { points += p; }

    @Override
    public String toString() {
        return "Customer{name='" + name + "', email='" + email + "', address='" + address + "', points=" + points + "}";
    }
}