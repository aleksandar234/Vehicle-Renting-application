package com.raf.restdemo.service;

import com.raf.restdemo.dto.vehicletype.VehicleTypeCreateDto;
import com.raf.restdemo.dto.vehicletype.VehicleTypeDto;
import com.raf.restdemo.dto.vehicletype.VehicleTypeUpdateDto;

import java.util.List;

public interface VehicleTypeService {

    List<VehicleTypeDto> getVehicleTypes(Long firmId);

    VehicleTypeDto createVehicleType(Long vehicleId, VehicleTypeCreateDto vehicleTypeCreateDto);

    VehicleTypeDto updateVehicleType(Long id, VehicleTypeUpdateDto vehicleTypeUpdateDto);

    void deleteVehicleType(Long id);

}
