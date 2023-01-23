package com.raf.restdemo.dto.rank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RankCreateDto {

    @NotEmpty
    private String name;

    @NotNull
    private int reservationLimit;

    @NotNull
    @DecimalMax("1.0") @DecimalMin("0.0")
    private double discount;

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
