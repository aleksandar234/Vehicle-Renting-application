package com.raf.restdemo.dto.firm;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FirmFilterPriceDto implements Comparable{

    private String firmId;
    private String firmName;
    private String firmDescription;
    private String firmCity;

    private String vehicleTypeId;
    private String vehicleTypeCategory;
    private String vehicleTypePrice;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getFirmDescription() {
        return firmDescription;
    }

    public void setFirmDescription(String firmDescription) {
        this.firmDescription = firmDescription;
    }

    public String getFirmCity() {
        return firmCity;
    }

    public void setFirmCity(String firmCity) {
        this.firmCity = firmCity;
    }

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleTypeCategory() {
        return vehicleTypeCategory;
    }

    public void setVehicleTypeCategory(String vehicleTypeCategory) {
        this.vehicleTypeCategory = vehicleTypeCategory;
    }

    public String getVehicleTypePrice() {
        return vehicleTypePrice;
    }

    public void setVehicleTypePrice(String vehicleTypePrice) {
        this.vehicleTypePrice = vehicleTypePrice;
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

    @Override
    public int compareTo(Object o) {
        return this.vehicleTypePrice.compareTo(((FirmFilterPriceDto)o).getVehicleTypePrice());
    }
}
