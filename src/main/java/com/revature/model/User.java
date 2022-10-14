package com.revature.model;

import java.util.Objects;

public class User {

    private static final int CUSTOMER = 1;
    private static final int RETAILER = 2;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int role;

    public User(){

    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String firstName, String lastName){
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = 1; // Default to customer
    }

    public User(String email, String password, String firstName, String lastName, int role){
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role; // Default to customer
    }

    public boolean isCustomer(){
        return this.role == CUSTOMER;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getRole() == user.getRole() && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), getFirstName(), getLastName(), getRole());
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName;
    }
}
