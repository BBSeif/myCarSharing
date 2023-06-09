package carsharing;

import java.util.List;

public interface CarDao {
    public void createTable();
    public void insertCar (String name, Company company);
    public List<Car> selectCar( Company company);
    void deleteTable();
}
