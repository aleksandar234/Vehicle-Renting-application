package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.Vehicle;
import com.raf.restdemo.domain.VehicleType;
import com.raf.restdemo.dto.vehicletype.VehicleTypeCreateDto;
import com.raf.restdemo.dto.vehicletype.VehicleTypeDto;
import com.raf.restdemo.dto.vehicletype.VehicleTypeUpdateDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleTypeMapper {

    public VehicleTypeDto vehicleTypeToVehicleTypeDto(VehicleType vehicleType){

        VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();
        vehicleTypeDto.setBrand(vehicleType.getBrand());
        vehicleTypeDto.setId(vehicleType.getId());
        vehicleTypeDto.setCategory(vehicleType.getCategory());
        vehicleTypeDto.setPrice(vehicleType.getPricePerDay());
        vehicleTypeDto.setFirm(vehicleType.getFirm().getName());

        return vehicleTypeDto;
    }

    public VehicleType vehicleTypeCreateDtoToVehicleType(VehicleTypeCreateDto vehicleTypeCreateDto){
        VehicleType vehicleType = new VehicleType();

        vehicleType.setBrand(vehicleTypeCreateDto.getBrand());
        vehicleType.setCategory(vehicleTypeCreateDto.getCategory());
        vehicleType.setPricePerDay(vehicleTypeCreateDto.getPrice());

        return vehicleType;
    }

    public void updateVehicleType(VehicleType vehicleType, VehicleTypeUpdateDto vehicleTypeUpdateDto){
        if(vehicleTypeUpdateDto.getCategory() != null){
            vehicleType.setCategory(vehicleTypeUpdateDto.getCategory());
        }
        if(vehicleTypeUpdateDto.getPrice() != 0){
            vehicleType.setPricePerDay(vehicleTypeUpdateDto.getPrice());
        }
        if(vehicleTypeUpdateDto.getBrand() != null) {
            vehicleType.setBrand(vehicleTypeUpdateDto.getBrand());
        }
    }

}
