package com.raf.restdemo.dto.vehicletype;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VehicleTypeUpdateDto {

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

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
