package carsharing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao{

    private Database db;
    private Connection con;

    public CompanyDaoImpl(Database db) {
        this.db = db;
        this.con = db.getCon();
    }
    @Override
    public void createTable() {
        try {
            Statement st = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS COMPANY " +
                    "(ID INTEGER AUTO_INCREMENT, "+
                    "NAME VARCHAR(50) UNIQUE NOT NULL,"+
                    "PRIMARY KEY (ID))";
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("DB is created!");
    }

    @Override
    public void insertCompany(String name) {
        try {
            Statement st = con.createStatement();
            String sql = "INSERT INTO COMPANY (NAME) VALUES ('" + name + "')";
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("DB is inserted");
    }

    @Override
    public List<Company> selectCompany() {
        List<Company> companies = new ArrayList<>();
        try  {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM COMPANY";
            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                companies.add(new Company(id, name));
            }
            st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("DB is selected");
        return companies;
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
