package com.revature.springboot.ServiceTest;


import com.revature.springboot.Repository.UserRepo;
import com.revature.springboot.Service.RegistrationService;
import com.revature.springboot.exceptions.InvalidInputException;
import com.revature.springboot.exceptions.QueryException;
import com.revature.springboot.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {

    @Mock
    UserRepo ur;

    @InjectMocks
    RegistrationService rs;


    //----------------------------------------------------------------------------------------------------------------//
    // Tests
    //----------------------------------------------------------------------------------------------------------------//


    // validPassword -- 6 < len < 20
    @Test
    public void validPassword_INPUT_length0_EXPECT_False() {
        Assertions.assertFalse(rs.validPassword(""));
    }

    @Test
    public void validPassword_INPUT_length5_EXPECT_False() {
        Assertions.assertFalse(rs.validPassword("abcde"));
    }

    @Test
    public void validPassword_INPUT_length6_EXPECT_True() {
        Assertions.assertTrue(rs.validPassword("abcdef"));
    }

    @Test
    public void validPassword_INPUT_length20_EXPECT_True() {
        Assertions.assertTrue(rs.validPassword("abcdefghij012345678."));
    }

    @Test
    public void validPassword_INPUT_length21_EXPECT_False() {
        Assertions.assertFalse(rs.validPassword("abcdefghij012345678.,"));
    }

    // validFirstname -- 1 <= len <= 30, all letters
    @Test
    public void validFirstname_INPUT_length1_EXPECT_True(){
        Assertions.assertTrue( rs.validFirstname("X") );
    }

    @Test
    public void validFirstname_INPUT_length30_EXPECT_True(){
        Assertions.assertTrue( rs.validFirstname("Wolfeschlegelsteinhausenberger") ); // Unfortunately, his real name, Wolfeschlegelsteinhausenbergerdorff, was too long
    }

    @Test
    public void validFirstname_INPUT_length31_EXPECT_False(){
        Assertions.assertFalse( rs.validFirstname("Wolfeschlegelsteinhausenbergerz") );
    }

    @Test
    public void validFirstname_INPUT_specialCharSPACEandDASHandPERIOD_EXPECT_True(){
        Assertions.assertTrue( rs.validFirstname("Tran-Cruz Sr.") );
    }

    @Test
    public void validFirstname_INPUT_specialChar1_EXPECT_False(){
        Assertions.assertFalse( rs.validFirstname("X AE A-12") );
    }

    // validLastname-- 1 <= len <= 30, all letters
    @Test
    public void validLastname_INPUT_length1_EXPECT_True(){
        Assertions.assertTrue( rs.validLastname("X") );
    }

    @Test
    public void validLastname_INPUT_length30_EXPECT_True(){
        Assertions.assertTrue( rs.validLastname("Wolfeschlegelsteinhausenberger") );
    }

    @Test
    public void validLastname_INPUT_length31_EXPECT_False(){
        Assertions.assertFalse( rs.validLastname("Wolfeschlegelsteinhausenbergerz") );
    }

    @Test
    public void validLastname_INPUT_specialCharSPACEandDASH_EXPECT_True(){
        Assertions.assertTrue( rs.validLastname("Tran-De la Cruz Sr.") );
    }

    @Test
    public void validLastname_INPUT_specialChar1_EXPECT_False(){
        Assertions.assertFalse( rs.validLastname("X AE A-12") );
    }


    // Valid Last name
    @Test
    public void validEmail_INPUT_usedEmail_EXPECT_InvalidInputException(){
        String email = "shaferai210@gmail.com";

        ArrayList<User> userReturn = new ArrayList<>();
        userReturn.add(new User("shaferai210@gmail.com","A1dan$hafer", "Aidan", "Shafer"));

        when(ur.findByEmail(email)).thenReturn( Optional.ofNullable(userReturn) );

        try {
            Assertions.assertThrows(InvalidInputException.class, ()->{rs.validEmail(email);} );
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }

    @Test
    public void validEmail_INPUT_newEmail_EXPECT_True(){
        String email = "shaferai@gmail.com";

        ArrayList<User> userReturn = new ArrayList<>();
        when(ur.findByEmail(email)).thenReturn( Optional.ofNullable(userReturn) );

        try {
            Assertions.assertTrue(rs.validEmail(email));
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }

}

