package carsharing;

import carsharing.CarDao;
import carsharing.Company;
import carsharing.CompanyDao;
import carsharing.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {

    private Database db;
    private Connection con;

    public CarDaoImpl(Database db) {
        this.db = db;
        this.con = db.getCon();
    }
    @Override
    public void createTable() {
        try {
            Statement st = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS CAR " +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT,"+
                    " NAME VARCHAR(255) NOT NULL,"+
                    "COMPANY_ID INT NOT NULL,"+
                    " FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID));";
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("DB is created!");
    }

    @Override
    public void insertCar(String name, Company company) {
        try {
            Statement st = con.createStatement();
            String sql = "INSERT INTO CAR (NAME, COMPANY_ID) VALUES ('" + name + "',"+ company.id()+ ")";
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("DB is inserted");
    }

    @Override
    public List<Car> selectCar( Company company) {
        List<Car> cars = new ArrayList<>();
        try  {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM CAR WHERE COMPANY_ID=" + company.id();
            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                int company_id = resultSet.getInt("COMPANY_ID");

                cars.add(new Car(id, name, company_id));
            }
            st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("DB is selected");
        return cars;
    }

    @Override
    public void deleteTable() {
        try {
            Statement st = con.createStatement();
            String sql = "DROP TABLE CAR";
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("bd is deleted");
    }
}
