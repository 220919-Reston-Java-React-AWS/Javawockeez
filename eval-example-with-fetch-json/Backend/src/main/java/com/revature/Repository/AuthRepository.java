package com.revature.Repository;

import com.revature.Model.Account;

import java.sql.*;

public class AuthRepository {

    // create a new Account
    public void createAccount(Account account) throws SQLException{
        try(Connection connection = ConnectionFactory.createConnection()){
            // SQL query to create a new Account record in Accounts database
            String sql = "INSERT INTO accounts (first_name, last_name, username, password) values (?, ?, ?, ?);";

            // create a PreparedStatement
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // set the placeholder values
            pstmt.setString(1, account.getFirstName());
            pstmt.setString(2, account.getLastName());
            pstmt.setString(3, account.getUsername());
            pstmt.setString(4, account.getPassword());

            // execute the query
            int numRecords = pstmt.executeUpdate();
        }
    }

    // get the name of Account with username and password
    public Account getName(Account account) throws SQLException {
        try(Connection connection = ConnectionFactory.createConnection()){
            // SQL query to create a new Account record in Accounts database
            String sql = "SELECT * FROM accounts AS a WHERE a.username = ? AND a.password = ?";

            // create a PreparedStatement
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // set the placeholder values
            pstmt.setString(1, account.getUsername());
            pstmt.setString(2, account.getPassword());

            //System.out.println(pstmt.toString());

            // execute the query, returns a result set
            ResultSet rs = pstmt.executeQuery();

            // if the rs pointer sees a record, transfer that data and return it
            if(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                return new Account(firstName,lastName);
            }

            return null;
        }
    }

    // update Account's firstname with username and password
    public void updateAccountFirstName(Account account) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            // SQL query to create a new Account record in Accounts database
            String sql = "UPDATE accounts SET first_name = ? WHERE username = ? AND password = ?";

            // create a PreparedStatement
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // set the placeholder values
            pstmt.setString(1, account.getFirstName());
            pstmt.setString(2, account.getUsername());
            pstmt.setString(3, account.getPassword());

//            System.out.println(pstmt.toString());

            // execute the query, returns a result set
            int numRecords = pstmt.executeUpdate();

        }
    }

    // update Account's lastname with username and password
    public void updateAccountLastName(Account account) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            // SQL query to create a new Account record in Accounts database
            String sql = "UPDATE accounts SET last_name = ? WHERE username = ? AND password = ?";

            // create a PreparedStatement
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // set the placeholder values
            pstmt.setString(1, account.getLastName());
            pstmt.setString(2, account.getUsername());
            pstmt.setString(3, account.getPassword());


//            System.out.println(pstmt.toString());

            // execute the query, returns a result set
            int numRecords = pstmt.executeUpdate();

        }
    }

    // delete Account from database
    public void deleteAccount(Account account) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            // SQL query to create a new Account record in Accounts database
            String sql = "DELETE FROM accounts WHERE username = ? AND password = ?;";

            // create a PreparedStatement
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // set the placeholder values
            pstmt.setString(1, account.getUsername());
            pstmt.setString(2, account.getPassword());


//            System.out.println(pstmt.toString());

            // execute the query, returns a result set
            int numRecords = pstmt.executeUpdate();

        }
    }

}
