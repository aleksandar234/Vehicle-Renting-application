package com.raf.restdemo.repository;

import com.raf.restdemo.domain.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {

    List<VehicleType> findAllByFirmId(Long firmId);

}
