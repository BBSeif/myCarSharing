package carsharing;

import java.util.List;

public interface CustomerDao {
    void createTable();
    void insertCustomer (String name);
    List<Customer> selectCustomer();
    void rentCar (String name, Car car);
    void returnRentCar (String name);

    //void checkMyList
    void deleteTable ();
}
