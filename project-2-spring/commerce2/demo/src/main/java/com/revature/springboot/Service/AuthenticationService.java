package com.revature.springboot.Service;

import com.revature.springboot.Repository.UserRepo;
import com.revature.springboot.exceptions.QueryException;
import com.revature.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthenticationService {

    // The user repository to use. Spring should allow only one throughout the app (to save space)
    @Autowired
    UserRepo ur;


    public User login(String email, String password) throws QueryException {
        Optional<List<User>> realUser = ur.findByEmail(email);

        if (realUser.isEmpty() | realUser.get().isEmpty()) {
            throw new QueryException("There are no accounts for that email address");
        } else {
            User usr = realUser.get().get(0); //There should only be one user with the email
            if ( usr.getPassword().equals(password) ){
                usr.secure();
                return usr;
            } else {
                throw new QueryException("Your password was incorrect");
            }
        }
    }

    public User getUserById(int id) throws QueryException {
        Optional<User> user = ur.findById(id);

        if ( user.isEmpty() ){
            throw new QueryException("That user couldn't be found");
        } else {
            return user.get();
        }
    }

}
