package com.raf.restdemo.dto.firm;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FirmFilterDto {

    private String name;
    private String city;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    private String priceSort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getPriceSort() {
        return priceSort;
    }

    public void setPriceSort(String priceSort) {
        this.priceSort = priceSort;
    }
}
