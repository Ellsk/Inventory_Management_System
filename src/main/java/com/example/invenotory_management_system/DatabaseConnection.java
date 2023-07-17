package com.example.invenotory_management_system;
import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
    public Connection getConnection(){
        String databaseName = "inventory_system";
        String databaseUser = "root";
        String databasePassword = "$Elliot1234";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

/*public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "inventory_system";
        String databaseUser = "root";
        String databasePassword = "$Elliot1234";
        String url = "jdbc:mysql://localhost/" + databaseName;


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }
        catch (Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
        return databaseLink;
    }
}*/




