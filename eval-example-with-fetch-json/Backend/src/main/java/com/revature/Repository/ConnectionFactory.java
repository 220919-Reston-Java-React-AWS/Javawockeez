package com.revature.Repository;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection createConnection() throws SQLException {
        Driver postgresDriver = new Driver();
        DriverManager.registerDriver(postgresDriver);

        String url = "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public";
        String username = System.getenv("PostgresUsername");
        String password = System.getenv("PostgresPassword");

        return DriverManager.getConnection(url, username, password);
    }

}
