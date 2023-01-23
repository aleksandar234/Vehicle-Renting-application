package com.raf.restdemo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle_types")
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private double pricePerDay;
    private String category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicleType")
    private List<Vehicle> vehicles = new ArrayList<>();

    @ManyToOne
    private Firm firm;

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

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }
}
