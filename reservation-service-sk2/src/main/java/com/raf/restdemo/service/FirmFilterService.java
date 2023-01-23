package com.raf.restdemo.service;

import com.raf.restdemo.dto.firm.FirmFilterDto;
import com.raf.restdemo.dto.firm.FirmFilterPriceDto;
import com.raf.restdemo.dto.vehicle.VehicleDto;
import com.raf.restdemo.dto.vehicletype.DateDto;
import com.raf.restdemo.dto.vehicletype.VehicleTypeDateDto;
import com.raf.restdemo.dto.vehicletype.VehicleTypeDto;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface FirmFilterService {

    List<VehicleTypeDto> findFirms(String name, Pageable pageable);
    List<VehicleTypeDto> findCities(String city, Pageable pageable);
    List<VehicleTypeDto> filterPerPrice(Pageable pageable);
    List<VehicleTypeDateDto> filterDates(DateDto dateDto);

}
