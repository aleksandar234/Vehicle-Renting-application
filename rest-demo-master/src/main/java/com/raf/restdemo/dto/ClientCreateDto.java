package com.raf.restdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.util.Date;

public class ClientCreateDto {

    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;
    private String firstName;
    private String lastName;
    private int rentedVehicles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public int getRentedVehicles() {
        return rentedVehicles;
    }

    public void setRentedVehicles(int rentedVehicles) {
        this.rentedVehicles = rentedVehicles;
    }


}
