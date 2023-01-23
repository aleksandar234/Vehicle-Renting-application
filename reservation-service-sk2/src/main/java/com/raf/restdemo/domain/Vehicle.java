package com.raf.restdemo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Firm firm;
    @ManyToOne
    private VehicleType vehicleType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    private List<Period> periods = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

}
