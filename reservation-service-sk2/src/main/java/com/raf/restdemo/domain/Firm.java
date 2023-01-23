package com.raf.restdemo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "firms", indexes = {@Index(columnList = "name", unique = true)})
public class Firm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "firm")
    private List<VehicleType> vehicleModels = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "firm")
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "firm")
    private List<Review> reviews = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<VehicleType> getVehicleModels() {
        return vehicleModels;
    }

    public void setVehicleModels(List<VehicleType> vehicleModels) {
        this.vehicleModels = vehicleModels;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
