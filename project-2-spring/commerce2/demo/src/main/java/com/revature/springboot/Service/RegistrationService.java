package com.revature.springboot.Service;

import com.revature.springboot.Repository.UserRepo;
import com.revature.springboot.exceptions.InvalidInputException;
import com.revature.springboot.model.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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


@Component
public class RegistrationService {
    // -----------------------------------------         SET-UP         --------------------------------------------- //


    // The user repository to use. Spring should allow only one throughout the app (to save space)
    @Autowired
    UserRepo ur;


    // -----------------------------------------         MODIFIERS         ------------------------------------------ //

    // Add a user to the server
    public void register(User newUser) throws Exception {

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

        ur.save(newUser); // **-- Actually add the user --** //

    }


    // -----------------------------------------         VALIDATORS         ----------------------------------------- //

    // The methods below are very changeable if the database changes

    // Validate Password
    public boolean validPassword(String pwd){ //Only checking length for now (6 to 20)
        return (pwd.length()>5 && pwd.length()<21);
    }

    // Validate Username (formatting, not existence)
    public boolean validEmail(String email) throws Exception { //Only checking length for now, less than 256, the 'official' longest possible length

        // Check whether the user exists
        Optional<List<User>> realUser = ur.findByEmail(email);
        if ( realUser.isEmpty() ){
            throw new Exception("Internal Server error.");
        }

        List<User> userList = realUser.get();
        if (!userList.isEmpty()){
            throw new InvalidInputException("There is already an account registered with this email.");
        }

        // Check Length
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

