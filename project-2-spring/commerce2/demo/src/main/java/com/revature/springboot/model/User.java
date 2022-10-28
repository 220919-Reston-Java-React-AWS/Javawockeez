package com.revature.springboot.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name="users")
public @Data class User {

    private static final int CUSTOMER = 1;
    private static final int RETAILER = 2;

    @Id //Would specify that this field should be primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="email")
    private String email;

    @Column(name="pwd")
    private String password;

    @Column(name="first_name")
    private String firstName = "";// Default to empty

    @Column(name="last_name")
    private String lastName = ""; // Default to empty

    @Column(name="role_id")
    private int role = 1;// Default to Customer

    public User(){

    }

    // For login
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    // For registration
    public User(String email, String password, String firstName, String lastName){
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // for returning the user to the front-end
    public User(int id, String email, String password, String firstName, String lastName, int role){
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role; // Default to customer
    }

    public boolean isCustomer(){
        return this.role == CUSTOMER;
    }

    public boolean allFieldsFilled() {
        if (this==null){
            return false;
        } else if (this.email==null || this.email.isEmpty() ) {
            return false;
        } else if ( this.password==null || this.password.isEmpty() ) {
            return false;
        } else {
            return true;
        }
    }

    public void secure(){
        this.password = "";
    }

}
