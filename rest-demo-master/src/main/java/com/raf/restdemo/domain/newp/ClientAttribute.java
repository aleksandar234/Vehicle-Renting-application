package com.raf.restdemo.domain.newp;

import javax.persistence.*;

@Entity
public class ClientAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportNumber;
    private int reservationNumber;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "clientAttribute")
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
