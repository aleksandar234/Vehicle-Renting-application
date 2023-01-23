package com.raf.restdemo.dto.vehicletype;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VehicleTypeCreateDto {

    @NotNull
    private double price;

    @NotEmpty
    private String category;

    @NotEmpty
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
