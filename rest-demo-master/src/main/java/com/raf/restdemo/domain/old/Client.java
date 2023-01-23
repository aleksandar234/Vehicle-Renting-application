package com.raf.restdemo.domain.old;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    private Role role;
    private String banned;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getBanned() {
        return banned;
    }

    public void setBanned(String banned) {
        this.banned = banned;
    }
}
