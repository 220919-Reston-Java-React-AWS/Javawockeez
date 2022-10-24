package com.revature.service;


import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.QueryException;
import com.revature.model.User;
import com.revature.repository.UserRepo;

import java.sql.SQLException;

// This is the service layer for registration. All requests to enter/leave the system are passed here to be parsed and
// verified, and then passed on to the User repository layer.
//
// Methods:
//      Modifiers:
//          register - Add the user to the system.
//          remove - Remove the user from the system (and destroy all pertinent information).
//      Validators:
//          validPassword - Checks if the Password is valid (6 to 20 characters long)
//          validUsername - Checks if the Username is valid (6 to 20 characters long)
//          validFirstname - Checks if the First Name is valid (30 or fewer characters long, all letters or SPACE or DASH)
//          validLastname - Checks if the Last Name is valid (30 or fewer characters long, all letters or SPACE or DASH)

public class RegistrationService {
    // -----------------------------------------         SET-UP         --------------------------------------------- //

    // Access to the User repository (or rather, access to the thing which accesses the database)
    UserRepo ur = new UserRepo();

    // Empty constructor - This isn't an 'object' so much as a list of methods
    public RegistrationService(){

    }


    // -----------------------------------------         MODIFIERS         ------------------------------------------ //

    // Add a user to the server
    public void register(User newUser) throws SQLException, QueryException, InvalidInputException {
        if ( ur.userExists(newUser.getEmail()) ) { // Check if username has been taken
            throw new InvalidInputException("There is already an account registered with this email.");
        }
        if ( !newUser.allFieldsFilled() ) { // Check for null or empty fields
            throw new InvalidInputException("email and password are required fields.");
        }
        if ( !validFirstname(newUser.getFirstName()) ) {
            throw new InvalidInputException("First Name was invalid. Your name should be 30 characters or less and contain no special characters.");
        }
        if ( !validLastname(newUser.getLastName()) ) {
            throw new InvalidInputException("Last Name was invalid. Your name should be 30 characters or less and contain no special characters.");
        }
        if ( !validEmail(newUser.getEmail()) ) {
            throw new InvalidInputException("That email address is invalid.");
        }
        if ( !validPassword(newUser.getPassword()) ) {
            throw new InvalidInputException("Your password must be between 6 and 20 characters in length.");
        }

        ur.addUser(newUser); // **-- Actually add the user --** //

    }


    // -----------------------------------------         VALIDATORS         ----------------------------------------- //

    // The methods below are very changeable if the database changes

    // Validate Password
    public boolean validPassword(String pwd){ //Only checking length for now (6 to 20)
        return (pwd.length()>5 && pwd.length()<21);
    }

    // Validate Username (formatting, not existence)
    public boolean validEmail(String email){ //Only checking length for now, less than 256, the 'official' longest possible length
        return (email.length()<256);
    }

    // Validate Firstname
    public boolean validFirstname(String frst){ //Checks for length (30 or less), and ensures all characters are letters, SPACE, or - (No number's, sorry Elon Musk's kid)
        return ( frst.matches("[-a-z. A-Z]+")  &&  frst.length()<31 );
    }

    // Validate Lastname
    public boolean validLastname(String last){ //Checks for length (30 or less), and ensures all characters are letters, SPACE, or -
        return ( last.matches("[-a-z. A-Z]+")  &&  last.length()<31 );
    }

    // ** NOTE:  "[-a-z. A-Z]+" is regex. It looks for a dash, letters a-z, periods, spaces, and letters A-Z. Order matters. [ ]+ checks existence
}

