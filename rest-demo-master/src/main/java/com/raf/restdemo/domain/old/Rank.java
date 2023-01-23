package com.raf.restdemo.domain.old;

import javax.persistence.*;

@Entity
@Table(name = "ranks")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private int reservationLimit;
    private double discount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReservationLimit() {
        return reservationLimit;
    }

    public void setReservationLimit(int reservationLimit) {
        this.reservationLimit = reservationLimit;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
