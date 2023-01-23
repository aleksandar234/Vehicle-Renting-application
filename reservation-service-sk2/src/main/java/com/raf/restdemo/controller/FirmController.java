package com.raf.restdemo.controller;

import com.raf.restdemo.dto.firm.FirmCreateDto;
import com.raf.restdemo.dto.firm.FirmDto;
import com.raf.restdemo.dto.firm.FirmUpdateDto;
import com.raf.restdemo.dto.vehicle.VehicleDto;
import com.raf.restdemo.dto.vehicletype.DateDto;
import com.raf.restdemo.dto.vehicletype.VehicleTypeDateDto;
import com.raf.restdemo.dto.vehicletype.VehicleTypeDto;
import com.raf.restdemo.security.CheckSecurity;
import com.raf.restdemo.service.FirmFilterService;
import com.raf.restdemo.service.FirmService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/firms")
public class FirmController {

    private final FirmService firmService;
    private final FirmFilterService firmFilterService;

    public FirmController(FirmService firmService, FirmFilterService firmFilterService) {
        this.firmService = firmService;
        this.firmFilterService = firmFilterService;
    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<FirmDto> createFirm(@RequestHeader("Authorization") String authorization, @RequestBody @Valid FirmCreateDto firmCreateDto){
        return new ResponseEntity<>(firmService.createFirm(firmCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"USER_ADMIN", "USER_MANAGER"})
    public ResponseEntity<FirmDto> getFirmById(@RequestHeader("Authorization") String authorization, @PathVariable Long id){
        return new ResponseEntity<>(firmService.getFirmById(id), HttpStatus.FOUND);
    }

//    @PutMapping("/{hotel-id}")
//    public ResponseEntity<FirmDto> updateFirm(@RequestHeader("Authorization") String authorization, @PathVariable("hotel-id") Long firmId, @RequestBody FirmUpdateDto firmUpdateDto){
//        return new ResponseEntity<>(firmService.updateFirm(firmId, firmUpdateDto), HttpStatus.OK);
//    }
//
    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"USER_ADMIN"})
    public ResponseEntity<HttpStatus> deleteFirm(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        firmService.deleteFirm(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @CheckSecurity(roles = {"USER_ADMIN", "USER_MANAGER", "USER_CLIENT"})
    public ResponseEntity<List<FirmDto>> getAllFirms(@RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(firmService.getAllFirms(), HttpStatus.OK);
    }

    @GetMapping("/managerHotel")
    public ResponseEntity<FirmDto> getManagerFirm(@RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(firmService.getFirmByManager(authorization), HttpStatus.OK);
    }

    @GetMapping("/cityName/{name}")
    public ResponseEntity<List<VehicleTypeDto>> findCities(@PathVariable("name") String name){
        return new ResponseEntity<>(firmFilterService.findCities(name, PageRequest.of(0, 20)), HttpStatus.OK);
    }

    @GetMapping("/firmName/{name}")
    public ResponseEntity<List<VehicleTypeDto>> findFirms(@PathVariable("name") String name){
        return new ResponseEntity<>(firmFilterService.findFirms(name, PageRequest.of(0, 20)), HttpStatus.OK);
    }

    @GetMapping("/filterPrice")
    public ResponseEntity<List<VehicleTypeDto>> filterPrices(){
        return new ResponseEntity<>(firmFilterService.filterPerPrice(PageRequest.of(0, 20)), HttpStatus.OK);
    }

    @PostMapping("/filterDates")
    public ResponseEntity<List<VehicleTypeDateDto>> filterDates(@RequestBody @Valid DateDto dateDto){
        return new ResponseEntity<>(firmFilterService.filterDates(dateDto), HttpStatus.OK);
    }

}
