package com.raf.restdemo.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ReservationCreateDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Long vehicleTypeId;
    private Long firmId;

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

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Long getFirmId() {
        return firmId;
    }

    public void setFirmId(Long firmId) {
        this.firmId = firmId;
    }
}
