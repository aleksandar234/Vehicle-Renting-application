package com.raf.restdemo.repository;

import com.raf.restdemo.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findVehicleByIdAndFirmId(Long id, Long firmId);

}
