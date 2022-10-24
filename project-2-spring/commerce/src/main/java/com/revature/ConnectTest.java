package com.revature;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectTest {

    @GetMapping("/")
    public String mainPage(){
        return "Connection Successful!!";
    }
}
