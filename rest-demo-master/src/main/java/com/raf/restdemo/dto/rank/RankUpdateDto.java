package com.raf.restdemo.dto.rank;

public class RankUpdateDto {

    private String name;
    private int reservationLimit;
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
