package carsharing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Database dbCarsharing = new Database("carsahring");
        CompanyDaoImpl companyDaoImpl = new CompanyDaoImpl(dbCarsharing);
        CarDaoImpl carDaoImpl = new CarDaoImpl(dbCarsharing);
        CoustomerDaoImpl coustomerDaoImpl = new CoustomerDaoImpl(dbCarsharing);

        companyDaoImpl.createTable();
        carDaoImpl.createTable();
        coustomerDaoImpl.createTable();

        Scanner scanner = new Scanner(System.in);
        startMenu();
        int option = scanner.nextInt();
        while (option != 0) {
            List<Customer> customerList = coustomerDaoImpl.selectCustomer();
            List<Company> companyList = companyDaoImpl.selectCompany();
            List<Car> carList = null;
            if (option == 1) {

                secondMenu();
                option = scanner.nextInt();
                while (option != 0) {
                    if (option == 1) {


                        if (companyList.isEmpty()) {
                            System.out.println("The company list is empty!");
                        } else {
                            System.out.println("Company list:");
                            companyList.forEach(System.out::println);
                            System.out.println("0. Back");
                            System.out.println();
                            option = scanner.nextInt();
                            while (option != 0) {
                                int num = option - 1;
                                Company comp = companyList.get(num);
                                System.out.println("'" + comp.name()+ "'" +" company");
                                therdMenu();
                                System.out.println();
                                option = scanner.nextInt();
                                while (option != 0) {
                                    if (option == 1) {
                                        carList = carDaoImpl.selectCar(comp);
                                        if (carList.isEmpty()) {
                                            System.out.println("The car list is empty!");
                                        } else {
                                        System.out.println("'" + comp.name() +"'"+ " cars:");
                                            int i = 0;
                                            for (Car car : carList) {
                                                System.out.println(++i + ". " + car);
                                            }
                                        System.out.println();
                                        }
                                    } else if (option == 2) {
                                        System.out.println("Enter the car name:");
                                        String name = scanner.nextLine();
                                        if (name.isEmpty()) {
                                            name = scanner.nextLine();
                                        }
                                        carDaoImpl.insertCar(name, comp);
                                        System.out.println("The car was added!");

                                    }
                                    System.out.println("'" + comp.name() + "'" + " cars:");
                                    therdMenu();
                                    option = scanner.nextInt();
                                }
                            }
                        }
                    } else if (option == 2) {
                        System.out.println("Enter the company name:");
                        String name = scanner.nextLine();
                        if (name.isEmpty()) {
                            name = scanner.nextLine();
                        }
                        companyDaoImpl.insertCompany(name);
                        System.out.println("The company was created!");
                    }
                    secondMenu();
                    option = scanner.nextInt();
                }
            } else if (option == 2) {
                if (customerList.isEmpty()) {
                    System.out.println("The customer list is empty!");
                    System.out.println();
                } else {
                    System.out.println("The customer list:");
                    customerList.forEach(System.out::println);
                    System.out.println("0. Back");
                    System.out.println();
                    option = scanner.nextInt();

                    while (option != 0) {
                        int num = option - 1;
                        if (customerList.isEmpty()) {
                            System.out.println("Customer list is empty.");
                            System.out.println();
                        } else {
                            Customer customer = customerList.get(num);
                            firstMenu();
                            option = scanner.nextInt();
                            while (option != 0) {
                                if ( option == 1) {
                                    if(companyList.isEmpty()) {

                                    } else {
                                        System.out.println("Choose a company:");
                                        companyList.forEach(System.out::println);
                                        System.out.println("0. Back");
                                        option = scanner.nextInt();
                                        num = option-1;
                                        Company comp = companyList.get(num);
                                        while (option != 0) {
                                            carList = carDaoImpl.selectCar(comp);
                                            System.out.println("Choose a car:");
                                            carList.forEach(System.out::println);
                                            System.out.println("0. Back");
                                            option = scanner.nextInt();
                                            num = option-1;
                                            Car car = carList.get(num);

                                            coustomerDaoImpl.rentCar(customer.name(), car);
                                            System.out.println();
                                        }
                                    }
                                    //coustomerDaoImpl.rentCar();
                                } else if (option == 2) {
                                    if ( )
                                    //coustomerDaoImpl.returnRentCar();
                                } else if (option == 3) {

                                }
                            }
                            firstMenu();
                            option = scanner.nextInt();
                        }
                    }
                }
            } else if (option == 3) {
                System.out.println("Enter the customer name:");
                String name = scanner.nextLine();
                if (name.isEmpty()) {
                    name = scanner.nextLine();
                }
                coustomerDaoImpl.insertCustomer(name);
                System.out.println("The customer was created!");
            }
            startMenu();
            option = scanner.nextInt();
        }

        dbCarsharing.closeConnection();



    }
    public static void startMenu() {
        System.out.println("1. Log in as a manager\n" +
                            "2. Log in as a customer\n" +
                            "3. Create a customer\n" +
                            "0. Exit");
    }

    public static void secondMenu() {
        System.out.println("1. Company list\n" +
                            "2. Create a company\n" +
                            "0. Back");
    }

    public static void therdMenu() {
        System.out.println("1. Car list\n" +
                            "2. Create a car\n" +
                            "0. Back");
    }
    public static void firstMenu() {
        System.out.println("1. Rent a car\n" +
                "2. Return a rented car\n" +
                "3. My rented car\n" +
                "0. Back");

    }

}
