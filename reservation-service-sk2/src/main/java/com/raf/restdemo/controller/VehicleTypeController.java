package com.raf.restdemo.controller;

import com.raf.restdemo.domain.VehicleType;
import com.raf.restdemo.dto.vehicletype.VehicleTypeCreateDto;
import com.raf.restdemo.dto.vehicletype.VehicleTypeDto;
import com.raf.restdemo.dto.vehicletype.VehicleTypeUpdateDto;
import com.raf.restdemo.security.CheckSecurity;
import com.raf.restdemo.service.VehicleTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicleTypes")
public class VehicleTypeController {

    private final VehicleTypeService vehicleTypeService;

    public VehicleTypeController(VehicleTypeService vehicleTypeService) {
        this.vehicleTypeService = vehicleTypeService;
    }

    @PostMapping("/{firmId}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<VehicleTypeDto> createVehicleType(@RequestHeader("Authorization") String authorization, @PathVariable("firmId") Long firmId, @RequestBody VehicleTypeCreateDto vehicleTypeCreateDto){
        return new ResponseEntity<>(vehicleTypeService.createVehicleType(firmId, vehicleTypeCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/{firmId}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<List<VehicleTypeDto>> getVehicleTypes(@RequestHeader("Authorization") String authorization, @PathVariable("firmId") Long firmId) {
        return new ResponseEntity<>(vehicleTypeService.getVehicleTypes(firmId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<HttpStatus> deleteVehicleType(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        vehicleTypeService.deleteVehicleType(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<VehicleTypeDto> updateVehicleType(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id, @RequestBody VehicleTypeUpdateDto vehicleTypeUpdateDto){
        return new ResponseEntity<>(vehicleTypeService.updateVehicleType(id, vehicleTypeUpdateDto), HttpStatus.OK);
    }

}
