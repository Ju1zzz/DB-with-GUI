package com.company;

import com.company.basicClasses.Clients;
import com.company.basicClasses.Employees;
import com.company.basicClasses.Orders;
import com.company.basicClasses.Works;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBWorker {
    public static final String PATH_TO_DB_FILE = "CarRepair.db";
    public static final String URL = "jdbc:sqlite:" + PATH_TO_DB_FILE;
    public static Connection connection;

    public static void initDB() {
        try {
            connection = DriverManager.getConnection(URL);
            if (connection != null) {
                //createDB();
            }
        } catch (SQLException ex) {
            System.out.println("Ошибка подключения: " + ex);
        }
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void createDB() throws SQLException {
        Statement stat = connection.createStatement();
        stat.execute("CREATE TABLE if not exists 'works' ('id' integer, 'title' TEXT PRIMARY KEY, 'hours' float, 'price' float);");
        stat.execute("CREATE TABLE if not exists 'employees' ('id' integer, 'name' TEXT PRIMARY KEY, 'job' text);");
        stat.execute("CREATE TABLE if not exists 'clients' ('id' integer, 'name' TEXT PRIMARY KEY, 'phone' text, 'car' text);");
        stat.execute("CREATE TABLE if not exists 'CurrentOrders' ('id' INTEGER PRIMARY KEY,  " +
                "'work_name' TEXT NOT NULL, 'emp_name' TEXT NOT NULL, 'car' TEXT NOT NULL, 'comment' text, FOREIGN KEY (work_name) REFERENCES works (title)ON DELETE CASCADE FOREIGN KEY (emp_name) REFERENCES employees (name)ON DELETE CASCADE FOREIGN KEY (car) REFERENCES clients (car)ON DELETE CASCADE);");
        System.out.println("Таблицы созданы");
    }


        public static void addClient(Clients client) throws SQLException {
            PreparedStatement statement=connection.prepareStatement("INSERT INTO clients('id','name','phone', 'car') " +
                    "VALUES(?,?,?,?);");
            statement.setObject(1, client.getId());
            statement.setObject(2, client.getName());
            statement.setObject(3, client.getPhone());
            statement.setObject(4,client.getCar());
            statement.executeUpdate();
            statement.close();
        }

        public static List<Clients> getAllclients() throws SQLException {
            Statement statement = connection.createStatement();
            List<Clients> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM clients");
            while (resultSet.next()) {
                list.add(new Clients(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("car")));
            }
            resultSet.close();
            statement.close();
            return list;
        }
        public static void deleteClients(Clients clients) throws SQLException
        {   Statement statement= connection.createStatement();
            statement.executeUpdate("DELETE FROM clients WHERE clients.id = "+clients.getId());
            statement.execute("UPDATE clients set id=id-1 WHERE id>"+clients.getId());
            System.out.println("deleted!");
            statement.close();
        }
        public static void deleteAllclients() throws SQLException {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM clients");
            System.out.println("all deleted!");
            statement.close();
        }
        public static void updateClient(int id, Clients clients) throws SQLException {
            PreparedStatement statement = connection.prepareStatement("UPDATE clients SET name=?, phone=?, car=? WHERE id=?");
            statement.setObject(1, clients.getName());
            statement.setObject(2, clients.getPhone());
            statement.setObject(3, clients.getCar());
            statement.setObject(4, id);
            statement.execute();
            statement.close();
    }

    public static void addEmp(Employees employees) throws SQLException {
        PreparedStatement statement=connection.prepareStatement("INSERT INTO employees('id','name','job') " +
                "VALUES(?,?,?);");
        statement.setObject(1, employees.getId());
        statement.setObject(2, employees.getName());
        statement.setObject(3, employees.getJob());
        statement.execute();
        statement.close();
    }
    public static List<Employees> getAllemps() throws SQLException {
        Statement statement = connection.createStatement();
        List<Employees> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM employees");
        while (resultSet.next()) {
            list.add(new Employees(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getString("job")));
        }
        resultSet.close();
        statement.close();
        return list;
    }
    public static void deleteEmps(Employees employees) throws SQLException
    {   Statement statement= connection.createStatement();
        statement.executeUpdate("DELETE FROM employees WHERE employees.id = "+employees.getId());
        statement.execute("UPDATE employees set id=id-1 WHERE id>"+employees.getId());
        System.out.println("deleted!");
        statement.close();
    }
    public static void deleteAllemps() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM employees");
        System.out.println("all deleted!");
        statement.close();
    }
    public static void updateEmp(int id, Employees employees) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE employees SET name=?, job=? WHERE id=?");
        statement.setObject(1, employees.getName());
        statement.setObject(2, employees.getJob());
        statement.setObject(3, id);
        statement.execute();
        statement.close();
    }

    public static void addWork(Works works) throws SQLException {
        PreparedStatement statement=connection.prepareStatement("INSERT INTO works('id','title','hours', 'price') " +
                "VALUES(?,?,?,?);");
        statement.setObject(1, works.getId());
        statement.setObject(2, works.getTitle());
        statement.setObject(3, works.getHours());
        statement.setObject(4, works.getPrice());
        statement.execute();
        statement.close();
    }
    public static List<Works> getAllworks() throws SQLException {
        Statement statement = connection.createStatement();
        List<Works> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM works");
        while (resultSet.next()) {
            list.add(new Works(resultSet.getInt("id"),resultSet.getString("title"), resultSet.getFloat("hours"), resultSet.getFloat("price")));
        }
        resultSet.close();
        statement.close();
        return list;
    }
    public static void deleteWorks(Works works) throws SQLException
    {   Statement statement= connection.createStatement();
        statement.executeUpdate("DELETE FROM works WHERE works.id = "+works.getId());
        statement.execute("UPDATE works set id=id-1 WHERE id>"+works.getId());
        System.out.println("deleted!");
        statement.close();
    }
    public static void deleteAllworks() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM works");
        System.out.println("all deleted!");
        statement.close();
    }
    public static void updateWork(int id, Works works) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE works SET title=?, hours=?, price=? WHERE id=?");
        statement.setObject(1, works.getTitle());
        statement.setObject(2, works.getHours());
        statement.setObject(3, works.getPrice());
        statement.setObject(4, id);
        statement.execute();
        statement.close();
    }

    public static List<Orders> getAllorders() throws SQLException {
        Statement statement = connection.createStatement();
        List<Orders> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM CurrentOrders");
        while (resultSet.next()) {
            list.add(new Orders(resultSet.getInt("id"),resultSet.getString("work_name"), resultSet.getString("emp_name"), resultSet.getString("car"),resultSet.getString("comment")));
        }
        resultSet.close();
        statement.close();
        return list;
    }
    public static void addOrder(Orders orders) throws SQLException {
        PreparedStatement statement=connection.prepareStatement("INSERT INTO CurrentOrders('id','work_name','emp_name', 'car','comment') " +
                "VALUES(?,?,?,?,?);");
        statement.setObject(1, orders.getId());
        statement.setObject(2, orders.getWork());
        statement.setObject(3, orders.getEmp());
        statement.setObject(4, orders.getCar());
        statement.setObject(5, orders.getComment());
        statement.execute();
        statement.close();
    }

    public static void deleteOrders(Orders orders) throws SQLException
    {   Statement statement= connection.createStatement();
        statement.executeUpdate("DELETE FROM CurrentOrders WHERE CurrentOrders.id = "+orders.getId());
        statement.execute("UPDATE CurrentOrders set id=id-1 WHERE id>"+orders.getId());
        System.out.println("deleted!");
        statement.close();
    }
    public static void deleteAllorders() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM CurrentOrders");
        System.out.println("all deleted!");
        statement.close();
    }
    public static String [] getAllempsName() throws SQLException {
        initDB();
        Statement statement = connection.createStatement();
        List <String> Emp = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT name FROM employees");
        while (resultSet.next()) {
            Emp.add(resultSet.getString("name"));
        }
        String [] emp = new String[Emp.size()];
        Emp.toArray(emp);
        resultSet.close();
        statement.close();
        closeConnection();
        return emp;
    }
    public static String [] getAllworksTitles() throws SQLException {
        initDB();
        Statement statement = connection.createStatement();
        List <String> works = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT title FROM works");
        while (resultSet.next()) {
            works.add(resultSet.getString("title"));
        }
        String [] work = new String[works.size()];
        works.toArray(work);
        resultSet.close();
        statement.close();
        closeConnection();
        return work;
    }
    public static String [] getAllcars() throws SQLException {
        initDB();
        Statement statement = connection.createStatement();
        List <String> cars = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT car FROM clients");
        while (resultSet.next()) {
            cars.add(resultSet.getString("car"));
        }
        String [] car = new String[cars.size()];
        cars.toArray(car);
        resultSet.close();
        statement.close();
        closeConnection();
        return car;
    }
    public static void updateOrder(int id, Orders orders) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE CurrentOrders SET work_name=?, emp_name=?, car=?, comment=? WHERE id=?");
        statement.setObject(1, orders.getWork());
        statement.setObject(2, orders.getEmp());
        statement.setObject(3, orders.getCar());
        statement.setObject(4, orders.getComment());
        statement.setObject(5, id);
        statement.execute();
        statement.close();
    }
}
