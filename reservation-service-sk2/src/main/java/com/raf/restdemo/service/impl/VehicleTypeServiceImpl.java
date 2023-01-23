package com.raf.restdemo.service.impl;

import com.raf.restdemo.domain.Vehicle;
import com.raf.restdemo.domain.VehicleType;
import com.raf.restdemo.dto.vehicletype.VehicleTypeCreateDto;
import com.raf.restdemo.dto.vehicletype.VehicleTypeDto;
import com.raf.restdemo.dto.vehicletype.VehicleTypeUpdateDto;
import com.raf.restdemo.mapper.VehicleTypeMapper;
import com.raf.restdemo.repository.FirmRepository;
import com.raf.restdemo.repository.VehicleRepository;
import com.raf.restdemo.repository.VehicleTypeRepository;
import com.raf.restdemo.service.VehicleTypeService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehicleTypeServiceImpl implements VehicleTypeService {


    private static final String firmNotFound = "Firm with given id not found!";

    private final VehicleTypeMapper vehicleTypeMapper;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final FirmRepository firmRepository;
    private final VehicleRepository vehicleRepository;

    public VehicleTypeServiceImpl(VehicleTypeMapper vehicleTypeMapper, VehicleTypeRepository vehicleTypeRepository, FirmRepository firmRepository, VehicleRepository vehicleRepository) {
        this.vehicleTypeMapper = vehicleTypeMapper;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.firmRepository = firmRepository;
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    public VehicleTypeDto createVehicleType(Long firmId, VehicleTypeCreateDto vehicleTypeCreateDto) {
        VehicleType vehicleType = vehicleTypeMapper.vehicleTypeCreateDtoToVehicleType(vehicleTypeCreateDto);
        try {

            vehicleType.setFirm(firmRepository.findById(firmId).orElseThrow(() -> new NotFoundException(firmNotFound)));
            Vehicle vehicle = new Vehicle();
            vehicle.setFirm(vehicleType.getFirm());
            vehicle.setVehicleType(vehicleType);
            vehicleType.getVehicles().add(vehicle);
            return vehicleTypeMapper.vehicleTypeToVehicleTypeDto(vehicleTypeRepository.save(vehicleType));

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<VehicleTypeDto> getVehicleTypes(Long firmId) {
        return vehicleTypeRepository.findAllByFirmId(firmId).stream().map(vehicleTypeMapper::vehicleTypeToVehicleTypeDto).collect(Collectors.toList());
    }

    @Override
    public VehicleTypeDto updateVehicleType(Long id, VehicleTypeUpdateDto vehicleTypeUpdateDto) {
        try {
            VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("Vehicle type not found!"));
            vehicleTypeMapper.updateVehicleType(vehicleType, vehicleTypeUpdateDto);
            VehicleTypeDto vtd = vehicleTypeMapper.vehicleTypeToVehicleTypeDto(vehicleTypeRepository.save(vehicleType));
            return vtd;
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteVehicleType(Long id) {
        vehicleTypeRepository.deleteById(id);
    }
}
