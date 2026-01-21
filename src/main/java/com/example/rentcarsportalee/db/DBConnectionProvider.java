package com.example.rentcarsportalee.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {

    private static DBConnectionProvider instance = new DBConnectionProvider();
    private Connection connection;

    private final String JDBC_URL = "jdbc:mysql://localhost:3306/rent_cars";
    private final String JDMBC_USERNAME = "root";
    private final String JDBC_PASSWORD = "adminroot";

    private DBConnectionProvider(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static DBConnectionProvider getInstance() {
        return instance;
    }

    public Connection getConnection() {
       try {
           if (connection == null || connection.isClosed()) {
               connection = DriverManager.getConnection(JDBC_URL,JDMBC_USERNAME,JDBC_PASSWORD);
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
        return connection;
    }
}
