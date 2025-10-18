package store;

public class Customer {
    private final String name;
<<<<<<< HEAD
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
=======

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

>>>>>>> ffbabdc674e6c3f42939875151af494898b1f728
