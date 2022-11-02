package com.revature.springboot.ServiceTest;

import com.revature.springboot.Repository.UserRepo;
import com.revature.springboot.Service.ProfileService;
import com.revature.springboot.Service.RegistrationService;
import com.revature.springboot.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @Mock
    UserRepo ur;

    @Mock
    RegistrationService rs;

    @InjectMocks
    ProfileService ps;


    //----------------------------------------------------------------------------------------------------------------//
    // Tests
    //----------------------------------------------------------------------------------------------------------------//


    // Merge Profiles
    @Test
    public void mergeProfiles_INPUT_emptyExceptEmail_EXPECT_changedEmail(){
        User original = new User("shaferai210@gmail.com","A1dan$hafer", "Aidan", "Shafer");
        User update = new User("shaferai@gmail.com", "", "", "");

        try {
            when(rs.validEmail(update.getEmail())).thenReturn(true);
            ps.mergeProfiles(original, update);
            Assertions.assertEquals(original, new User("shaferai@gmail.com", "A1dan$hafer", "Aidan", "Shafer"));
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }

    @Test
    public void mergeProfiles_INPUT_emptyExceptPassword_EXPECT_changedEmail(){
        User original = new User("shaferai210@gmail.com","A1dan$hafer", "Aidan", "Shafer");
        User update = new User("", "password", "", "");

        try{
            when(rs.validPassword(update.getPassword())).thenReturn(true);
            ps.mergeProfiles(original, update);

            Assertions.assertEquals(original, new User("shaferai210@gmail.com","password", "Aidan", "Shafer"));
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }

    @Test
    public void mergeProfiles_INPUT_emptyExceptFirstName_EXPECT_changedEmail(){
        User original = new User("shaferai210@gmail.com","A1dan$hafer", "Aidan", "Shafer");
        User update = new User("", "", "Chris", "");

        try {
            when(rs.validFirstname(update.getFirstName())).thenReturn(true);
            ps.mergeProfiles(original, update);

            Assertions.assertEquals(original, new User("shaferai210@gmail.com", "A1dan$hafer", "Chris", "Shafer"));
        } catch (Exception e) {
        System.out.println( e.getMessage() );
    }
    }

    @Test
    public void mergeProfiles_INPUT_emptyExceptLastName_EXPECT_changedEmail(){
        User original = new User("shaferai210@gmail.com","A1dan$hafer", "Aidan", "Shafer");
        User update = new User("", "", "", "McMillen");

        try{
            when(rs.validLastname(update.getLastName())).thenReturn(true);
            ps.mergeProfiles(original, update);

            Assertions.assertEquals(original, new User("shaferai210@gmail.com","A1dan$hafer", "Aidan", "McMillen"));
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }

    @Test
    public void mergeProfiles_INPUT_emptyExceptInvalidFirstName_EXPECT_changedEmail(){
        User original = new User("shaferai210@gmail.com","A1dan$hafer", "Aidan", "Shafer");
        User update = new User("", "", "Chris1", "");

        try {
            when(rs.validFirstname(update.getFirstName())).thenReturn(false);
            ps.mergeProfiles(original, update);

            Assertions.assertEquals(original, new User("shaferai210@gmail.com", "A1dan$hafer", "Aidan", "Shafer"));
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }

    @Test
    public void mergeProfiles_INPUT_emptyExceptInvalidLastName_EXPECT_changedEmail(){
        User original = new User("shaferai210@gmail.com","A1dan$hafer", "Aidan", "Shafer");
        User update = new User("", "", "", "McMillen1");

        try{
            when(rs.validLastname(update.getLastName())).thenReturn(false);
            ps.mergeProfiles(original, update);

            Assertions.assertEquals(original, new User("shaferai210@gmail.com","A1dan$hafer", "Aidan", "Shafer"));
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }

    @Test
    public void mergeProfiles_INPUT_allExceptPassword_EXPECT_allChangedExceptPassword(){
        User original = new User("shaferai210@gmail.com","A1dan$hafer", "Aidan", "Shafer");
        User update = new User("shaferai@gmail.com", "", "Chris", "McMillen");

        try{
            when(rs.validEmail(update.getEmail())).thenReturn(true);
            when(rs.validFirstname(update.getFirstName())).thenReturn(true);
            when(rs.validLastname(update.getLastName())).thenReturn(true);
            ps.mergeProfiles(original, update);

            Assertions.assertEquals(original, new User("shaferai@gmail.com","A1dan$hafer", "Chris", "McMillen"));
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }

}
