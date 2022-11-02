package com.revature.springboot.Service;

import com.revature.springboot.Repository.UserRepo;
import com.revature.springboot.exceptions.InvalidInputException;
import com.revature.springboot.exceptions.QueryException;
import com.revature.springboot.model.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    UserRepo ur;

    @Autowired
    RegistrationService rs;


    public User getProfile(int userId) throws QueryException {
        Optional<User> userOpt = ur.findById(userId);

        if (userOpt.isEmpty()){
            throw new QueryException("The user is not in the datebase");
        }

        User user = userOpt.get();
        user.secure();

        return user;
    }

    public User updateProfile(int userId, User updatedProfile) throws Exception {

        Optional<User> original = ur.findById(userId);

        if (original.isEmpty()){
            throw new QueryException("That User is not in the database");
        }

        User orig = original.get();
        mergeProfiles(orig, updatedProfile);

        ur.save( orig );

        return orig;

    }

    public void mergeProfiles(User original, User update) throws Exception {

        String newEmail = update.getEmail();
        if ( newEmail!=null ){
            if ( !newEmail.isEmpty() & rs.validEmail(newEmail) ) {
                original.setEmail(newEmail);
            }
        }

        String newPassword = update.getPassword();
        if ( newPassword!=null ){
            if ( !newPassword.isEmpty() & rs.validPassword(newPassword)) {
                original.setPassword(newPassword);
            }
        }

        String newFirst = update.getFirstName();
        if ( newFirst!=null ){
            if ( !newFirst.isEmpty() & rs.validFirstname(newFirst) ) {
                original.setFirstName(newFirst);
            }
        }

        String newLast = update.getLastName();
        if ( newLast!=null ){
            if ( !newLast.isEmpty() & rs.validLastname(newLast) ) {
                original.setLastName(newLast);
            }
        }

    }

}
