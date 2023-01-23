package com.raf.restdemo.domain.newp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.raf.restdemo.domain.old.UserRole;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UserRole role;
    private boolean enabled;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private String phone;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthday;

    @OneToOne(cascade = CascadeType.ALL)
    private ClientAttribute clientAttribute;
    @OneToOne(cascade = CascadeType.ALL)
    private ManagerAttribute managerAttribute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public ClientAttribute getClientAttribute() {
        return clientAttribute;
    }

    public void setClientAttribute(ClientAttribute clientAttribute) {
        this.clientAttribute = clientAttribute;
    }

    public ManagerAttribute getManagerAttribute() {
        return managerAttribute;
    }

    public void setManagerAttribute(ManagerAttribute managerAttribute) {
        this.managerAttribute = managerAttribute;
    }
}
