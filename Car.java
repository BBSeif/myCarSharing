package carsharing;

public record Car(int id, String name, int company_id) {
    @Override
    public String toString() {
        return ( name );
    }
}
