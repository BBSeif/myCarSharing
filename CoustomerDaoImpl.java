package carsharing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CoustomerDaoImpl implements CustomerDao {

    private Database db;
    private Connection con;

    public CoustomerDaoImpl(Database db) {
        this.db = db;
        this.con = db.getCon();
    }
    @Override
    public void createTable() {
        try {
            Statement st = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS CUSTOMER " +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT,"+
                    " NAME VARCHAR(255) NOT NULL,"+
                    "RENTED_CAR_ID INT DEFAULT NULL," +
                    " FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID));";
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("DB is created!");
    }

    @Override
    public void insertCustomer(String name) {
        try {
            Statement st = con.createStatement();
            String sql = "INSERT INTO CUSTOMER (NAME) VALUES ('" + name + "')";
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("DB is inserted");
    }

    @Override
    public List<Customer> selectCustomer() {
        List<Customer> customers = new ArrayList<>();
        try  {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM CUSTOMER";
            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                int rent = resultSet.getInt("RENTED_CAR_ID");
                customers.add(new Customer(id, name, rent));
            }
            st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("DB is selected");
        return customers;
    }

    @Override
    public void rentCar(String name, Car car) {
        try {
            Statement st = con.createStatement();
            String sql = "UPDATE CUSTOMER " +
                    "SET RENTED_CAR_ID =" + car +
                    "WHERE NAME =" + name;
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("Car in rent");
    }

    @Override
    public void returnRentCar(String name) {
        try {
            Statement st = con.createStatement();
            String sql = "UPDATE CUSTOMER " +
                    "SET RENTED_CAR_ID = NULL" +
                    "WHERE NAME =" + name;
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("Car in rent is  NULLED");
    }

    @Override
    public void deleteTable() {
        try {
            Statement st = con.createStatement();
            String sql = "DROP TABLE COMPANY";
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("bd is deleted");
    }
}
