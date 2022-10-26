package com.revature.model;

import java.util.Objects;

public class User {

    private static final int CUSTOMER = 1;
    private static final int RETAILER = 2;


    private int id;
    private String email;
    private String password;
    private String firstName = "";// Default to empty
    private String lastName = ""; // Default to empty
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && getRole() == user.getRole() && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword(), getFirstName(), getLastName(), getRole());
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName;
    }
}
