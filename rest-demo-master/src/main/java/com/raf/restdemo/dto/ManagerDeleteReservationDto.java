package com.raf.restdemo.dto;

public class ManagerDeleteReservationDto {

    private int rentedVehicles;
    private String clientUsername;

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public int getRentedVehicles() {
        return rentedVehicles;
    }

    public void setRentedVehicles(int rentedVehicles) {
        this.rentedVehicles = rentedVehicles;
    }


}
