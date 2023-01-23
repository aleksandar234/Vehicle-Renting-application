package com.raf.restdemo.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ReservationDto {

    private Long id;
    private String username;

    private Long vehicleTypeId;
    private String vehicleTypeCategory;
    private String firmName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private double price;

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

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleTypeCategory() {
        return vehicleTypeCategory;
    }

    public void setVehicleTypeCategory(String vehicleTypeCategory) {
        this.vehicleTypeCategory = vehicleTypeCategory;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
