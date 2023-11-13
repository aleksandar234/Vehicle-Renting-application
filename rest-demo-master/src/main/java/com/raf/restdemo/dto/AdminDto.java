package com.raf.restdemo.dto;

// Razlog zasto uopste imamo i koristimo DTO (Data transfer object) je taj da ne bi slali nepotrebne ili osetljive podatke ako se to ne zahteva
public class AdminDto {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;


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
}
