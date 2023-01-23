package com.raf.restdemo.dto.vehicletype;

public class VehicleTypeDto {

    private Long id;
    private double price;
    private String category;
    private String brand;
    private String firm;

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
