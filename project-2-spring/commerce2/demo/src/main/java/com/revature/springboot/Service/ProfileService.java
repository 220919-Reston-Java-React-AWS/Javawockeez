package com.revature.springboot.Service;

import com.revature.springboot.Repository.UserRepo;
import com.revature.springboot.exceptions.QueryException;
import com.revature.springboot.model.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    UserRepo ur;


    public User getProfile(int userId) throws QueryException {
        Optional<User> userOpt = ur.findById(userId);

        if (userOpt.isEmpty()){
            throw new QueryException("The user is not in the datebase");
        }

        User user = userOpt.get();
        user.secure();

        return user;
    }

    public User updateProfile(int userId, User updatedProfile) throws QueryException {

        Optional<User> original = ur.findById(userId);

        if (original.isEmpty()){
            throw new QueryException("That User is not in the database");
        }

        User orig = original.get();
        mergeProfiles(orig, updatedProfile);

        ur.save( orig );

        return orig;

    }

    public void mergeProfiles(User original, User update){

        String newEmail = update.getEmail();
        if ( newEmail!=null ){
            if ( !newEmail.isEmpty() ) {
                original.setEmail(newEmail);
            }
        }

        String newPassword = update.getPassword();
        if ( newPassword!=null ){
            if ( !newPassword.isEmpty() ) {
                original.setPassword(newPassword);
            }
        }

        String newFirst = update.getFirstName();
        if ( newFirst!=null ){
            if ( !newFirst.isEmpty() ) {
                original.setFirstName(newFirst);
            }
        }

        String newLast = update.getLastName();
        if ( newLast!=null ){
            if ( !newLast.isEmpty() ) {
                original.setLastName(newLast);
            }
        }

    }

}
