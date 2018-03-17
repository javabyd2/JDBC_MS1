package com.sda.jdbc;

import java.sql.*;

public class Main {

    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/rental_db?useSSL=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "JavaRootMS1";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String insert = "insert into rent" +
                    "(cust_id, reg_number, rent_date, return_date)" +
                    "values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, 4);
            preparedStatement.setString(2, "SI 60606");
            //GregorianCalendar gregorianCalendar = new GregorianCalendar(2016, 3, 8);
            //Date date = new Date(gregorianCalendar.getGregorianChange());
            preparedStatement.setDate(3, java.sql.Date.valueOf("2016-03-08"));
            preparedStatement.setDate(4, java.sql.Date.valueOf("2016-05-05"));
            //preparedStatement.setDate(4, new GregorianCalendar(2016, 5, 5).getTime());
            preparedStatement.executeUpdate();

            String update = "update rent " +
                    "set rent_date = ?, return_date = ? " +
                    "where rent_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setDate(1, java.sql.Date.valueOf("2016-03-09"));
            preparedStatement.setDate(2, java.sql.Date.valueOf("2016-05-06"));
            preparedStatement.setInt(3, 7);
            preparedStatement.executeUpdate();

            String sql = "select * from rent r\n" +
                    "left join customer c on r.cust_id = c.cust_id";
            ResultSet resultSet = statement.executeQuery(sql);
            //System.out.println(resultSet);
            System.out.println("Wynajęcia:");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("first_name")
                        + " " + resultSet.getString("last_name")
                        + ", auto: " + resultSet.getString("reg_number")
                        + " od " + resultSet.getDate("rent_date")
                        + " do " + resultSet.getDate("return_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
