package com.revature.repository;

import com.revature.exceptions.QueryException;
import com.revature.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {

    // Column positions in the SQL database, used to simplify minor changes.
    //id = 1
    public final int FIRSTNAME_COLUMN = 2;
    public final int LASTNAME_COLUMN = 3;
    public final int EMAIL_COLUMN = 4;
    public final int PASSWORD_COLUMN = 5;
    public final int ROLE_ID_COLUMN = 6;


    // Check to see the password matches that of the user with a specific username.
    public boolean checkPassword(String email, String pwd_guess) throws SQLException, QueryException{
        try (Connection conn = ConnectionFactory.createConnection()){
            String sql = "SELECT * FROM users WHERE email=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next() ) {
                return pwd_guess.equals(rs.getString(PASSWORD_COLUMN));

            } else {
                throw new QueryException("The user " + email + " could not be found.");
            }
        }
    }

    // Check if the user exists in the database.
    public boolean userExists(String email) throws SQLException {
        try (Connection conn = ConnectionFactory.createConnection()){
            String sql = "SELECT * FROM users WHERE email=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            return rs.next();// If there is a record, this will return true
        }
    }

    public User getUserByEmail(String email)throws SQLException, QueryException {
        try (Connection conn = ConnectionFactory.createConnection()){
            String sql = "SELECT * FROM users WHERE email=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next() ) {
                return new User(rs.getString(EMAIL_COLUMN),
                        rs.getString(PASSWORD_COLUMN),
                        rs.getString(FIRSTNAME_COLUMN),
                        rs.getString(LASTNAME_COLUMN),
                        rs.getInt(ROLE_ID_COLUMN)
                );
            } else {
                throw new QueryException("The user " + email + " could not be found");
            }
        }
    }

    public static void main(String[] args) {

        try {
            UserRepo ur = new UserRepo();

            System.out.println(ur.getUserByEmail("shaferai210@gmail.com")); // Aidan Shafer
            System.out.println(ur.userExists("shaferai210@gmail.com")); // true
            System.out.println(ur.userExists("shafe@gmail.com")); // false
            System.out.println(ur.checkPassword("shaferai210@gmail.com", "Password")); // false
            System.out.println(ur.checkPassword("shaferai210@gmail.com", "A1dan$hafer")); // true
            System.out.println(ur.checkPassword("shafe@gmail.com", "A1dan$hafer")); // exception

        } catch (SQLException e){
            System.out.println( e.getMessage() );
        } catch (QueryException e){
            System.out.println( e.getMessage() );
        }
    }
}
