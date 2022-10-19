package com.revature.Service;

import com.revature.Model.Account;
import com.revature.Repository.AuthRepository;

import java.sql.SQLException;

public class AuthService {
    AuthRepository authRepository = new AuthRepository();

    public AuthService(){}  // default constructor

    // POST - create a new Account
    public void createAccount(Account account) throws SQLException {
        authRepository.createAccount(account);
    }

    // GET - get the name of Account based on username and password
    public Account getName(Account account) throws SQLException{
        Account response = authRepository.getName(account);

        // return object with the firstname and lastname
        return response;
    }

    // PATCH - update a record in database
    public void updateAccountName(Account account) throws SQLException{
        // if updating both first and last name
        if(account.getFirstName() != null && account.getLastName() != null) {
            authRepository.updateAccountFirstName(account);
            authRepository.updateAccountLastName(account);
        }
        //if updating firstname only
        else if(account.getFirstName() != null && account.getLastName() == null) {
            authRepository.updateAccountFirstName(account);
        }
        //if updating lastname only
        else if(account.getFirstName() == null && account.getLastName() != null){
            authRepository.updateAccountLastName(account);
        }
    }

    //DELETE - delete a record in datebase
    public void deleteAccount(Account account) throws SQLException{
        authRepository.deleteAccount(account);
    }
}
