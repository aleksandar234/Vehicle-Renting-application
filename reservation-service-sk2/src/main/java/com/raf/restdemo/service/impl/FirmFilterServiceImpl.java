package com.raf.restdemo.service.impl;

import com.raf.restdemo.domain.Firm;
import com.raf.restdemo.domain.Period;
import com.raf.restdemo.domain.Vehicle;
import com.raf.restdemo.domain.VehicleType;
import com.raf.restdemo.dto.vehicle.VehicleDto;
import com.raf.restdemo.dto.vehicletype.DateDto;
import com.raf.restdemo.dto.vehicletype.VehicleTypeDateDto;
import com.raf.restdemo.dto.vehicletype.VehicleTypeDto;
import com.raf.restdemo.mapper.FirmMapper;
import com.raf.restdemo.mapper.VehicleTypeMapper;
import com.raf.restdemo.repository.FirmRepository;
import com.raf.restdemo.repository.PeriodRepository;
import com.raf.restdemo.repository.VehicleTypeRepository;
import com.raf.restdemo.service.FirmFilterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

@Service
@Transactional
public class FirmFilterServiceImpl implements FirmFilterService {

    private static final String dateRequiredMessage = "Start date and end date are mandatory";


    private final VehicleTypeRepository vehicleTypeRepository;
    private final VehicleTypeMapper vehicleTypeMapper;
    private final FirmRepository firmRepository;
    private final PeriodRepository periodRepository;
    private final FirmMapper firmMapper;

    public FirmFilterServiceImpl(VehicleTypeRepository vehicleTypeRepository, VehicleTypeMapper vehicleTypeMapper, FirmRepository firmRepository, PeriodRepository periodRepository, FirmMapper firmMapper) {
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.vehicleTypeMapper = vehicleTypeMapper;
        this.firmRepository = firmRepository;
        this.periodRepository = periodRepository;
        this.firmMapper = firmMapper;
    }

    @Override
    public List<VehicleTypeDto> findCities(String name, Pageable pageable) {

        List<VehicleTypeDto> vehicleTypeDtos = new ArrayList<>();
        List<VehicleType> vehicleTypes;
        List<Firm> firms = firmRepository.findAll();for(Firm firm: firms) {
            if(firm.getCity().equals(name)){
                Long id = firm.getId();
                vehicleTypes = vehicleTypeRepository.findAllByFirmId(id);
                for(VehicleType v: vehicleTypes) {
                    VehicleTypeDto vtd = vehicleTypeMapper.vehicleTypeToVehicleTypeDto(v);
                    vehicleTypeDtos.add(vtd);
                }
            }
        }

        return vehicleTypeDtos;
    }

    @Override
    public List<VehicleTypeDto> filterPerPrice(Pageable pageable) {
        List<VehicleType> vehicleTypes;
        List<Firm> firms = firmRepository.findAll();
        List<VehicleTypeDto> vehicleTypeDtos = new ArrayList<>();
        List<Double> prices = new ArrayList<>();
        for(Firm firm: firms) {
            Long id = firm.getId();
            vehicleTypes = vehicleTypeRepository.findAllByFirmId(id);
            for(VehicleType vehicleType: vehicleTypes) {
                VehicleTypeDto vtd = vehicleTypeMapper.vehicleTypeToVehicleTypeDto(vehicleType);
                vehicleTypeDtos.add(vtd);
                prices.add(vtd.getPrice());
            }
        }
        List<VehicleTypeDto> vehicleTypeDtos2 = new ArrayList<>();
        Collections.sort(prices);
        for(Double price: prices) {
            for(VehicleTypeDto vehicleTypeDto: vehicleTypeDtos) {
                if(price == vehicleTypeDto.getPrice()) {
                    vehicleTypeDtos2.add(vehicleTypeDto);
                    vehicleTypeDtos.remove(vehicleTypeDto);
                    break;
                }
            }
        }

        return vehicleTypeDtos2;

    }


    @Override
    public List<VehicleTypeDto> findFirms(String firmName, Pageable pageable) {
        List<VehicleType> vehicleTypes = new ArrayList<>();
        List<Firm> firms = firmRepository.findAll();
        List<VehicleTypeDto> vehicleTypeDtos = new ArrayList<>();
        for(Firm firm: firms) {
            if(firm.getName().equals(firmName)){
                Long id = firm.getId();
                vehicleTypes = vehicleTypeRepository.findAllByFirmId(id);
                for(VehicleType v: vehicleTypes) {
                    VehicleTypeDto vtd = vehicleTypeMapper.vehicleTypeToVehicleTypeDto(v);
                    vehicleTypeDtos.add(vtd);
                }
            }
        }

        return vehicleTypeDtos;
    }


    @Override
    public List<VehicleTypeDateDto> filterDates(DateDto dateDto) {

        List<Vehicle> availableVehicles = new ArrayList<>();
        List<Firm> firms = firmRepository.findAll();
        for(Firm firm: firms) {
            for(Vehicle vehicle: firm.getVehicles()) {
                List<Period> p1 = periodRepository.findPeriodByStartDateBeforeAndEndDateAfterAndVehicleId(dateDto.getStartDate(), dateDto.getStartDate(), vehicle.getId());
                List<Period> p2 = periodRepository.findPeriodByStartDateBeforeAndEndDateAfterAndVehicleId(dateDto.getEndDate(), dateDto.getEndDate(), vehicle.getId());
                List<Period> p3 = periodRepository.findPeriodByStartDateAfterAndEndDateBeforeAndVehicleId(dateDto.getStartDate(), dateDto.getEndDate(), vehicle.getId());
                List<Period> p4 = periodRepository.findPeriodByStartDateAndEndDateAndVehicleId(dateDto.getStartDate(), dateDto.getEndDate(), vehicle.getId());
                if(p1.isEmpty() && p2.isEmpty() && p3.isEmpty() && p4.isEmpty()) availableVehicles.add(vehicle);
            }
        }

        List<VehicleTypeDateDto> vehicleTypeDtos = new ArrayList<>();
        VehicleTypeDateDto vehicleTypeDateDto;
        for(Vehicle v: availableVehicles) {
            vehicleTypeDateDto = new VehicleTypeDateDto();
            vehicleTypeDateDto.setId(v.getId());
            vehicleTypeDateDto.setFirmName(v.getFirm().getName());
            vehicleTypeDateDto.setBrand(v.getVehicleType().getBrand());
            vehicleTypeDateDto.setCategory(v.getVehicleType().getCategory());
            vehicleTypeDateDto.setPrice(v.getVehicleType().getPricePerDay());
            for(Period p: v.getPeriods()) {
                if(p.getVehicle().getId().equals(v.getId())) {
                    vehicleTypeDateDto.setStartDate(p.getStartDate());
                    vehicleTypeDateDto.setEndDate(p.getEndDate());
                }
            }
            vehicleTypeDtos.add(vehicleTypeDateDto);
        }
        return vehicleTypeDtos;
    }

}
