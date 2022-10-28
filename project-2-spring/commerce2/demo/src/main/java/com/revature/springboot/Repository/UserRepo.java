package com.revature.springboot.Repository;


import com.revature.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


// Tell spring to access the user repository, with User class and Integer primary key
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    public Optional<List<User>> findByEmail(String email);

}
