package com.raf.restdemo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "periods")
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-d")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ManyToOne
    private Vehicle vehicle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
