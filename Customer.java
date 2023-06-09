package carsharing;

public record Customer(int id, String name, int rented) {
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rented=" + rented +
                '}';
    }
}
