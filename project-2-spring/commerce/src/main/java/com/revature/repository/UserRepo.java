package com.revature.repository;

import com.revature.exceptions.QueryException;
import com.revature.model.User;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component
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

    public int getUserIDByEmail(String email) throws SQLException, QueryException {
        try (Connection conn = ConnectionFactory.createConnection()) {
            String sql = "SELECT id FROM users WHERE email=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new QueryException("The user " + email + " could not be found");
            }
        }
    }

    // Add an employee to the system, aka register. Defaults the role to employee
    public void addUser(User user) throws SQLException {
        try (Connection conn = ConnectionFactory.createConnection()){
            String sql = "INSERT INTO users(first_name, last_name, email, pwd, role_id) VALUES (?,?,?,?,?);";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString( 1, user.getFirstName() );
            pstmt.setString( 2, user.getLastName() );
            pstmt.setString( 3, user.getEmail() );
            pstmt.setString( 4, user.getPassword() );
            pstmt.setInt( 5, user.getRole() );

            pstmt.executeUpdate();
        }
    }



    public static void main(String[] args) {

        try {
            UserRepo ur = new UserRepo();

            User newUser = new User("test@gmail.com","password123","testy","mcTestface");

            System.out.println(ur.getUserIDByEmail("shaferai210@gmail.com"));
            //System.out.println(ur.getUserByEmail("shaferai210@gmail.com")); // Aidan Shafer
            //System.out.println(ur.userExists("shaferai210@gmail.com")); // true
            //System.out.println(ur.userExists("shafe@gmail.com")); // false
            //System.out.println(ur.checkPassword("shaferai210@gmail.com", "Password")); // false
            //System.out.println(ur.checkPassword("shaferai210@gmail.com", "A1dan$hafer")); // true
            //System.out.println(ur.checkPassword("shafe@gmail.com", "A1dan$hafer")); // exception
            //ur.addUser(newUser);



        } catch (Exception e){
            System.out.println( e.getMessage() );
        }
    }
}
