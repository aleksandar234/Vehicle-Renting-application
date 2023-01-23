package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.Period;
import com.raf.restdemo.domain.Vehicle;
import com.raf.restdemo.dto.period.PeriodCreateDto;
import com.raf.restdemo.repository.VehicleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PeriodMapper {

    private final VehicleRepository vehicleRepository;

    public PeriodMapper(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Period periodCreateDtoToPeriod(PeriodCreateDto periodCreateDto) {

        Period period = new Period();
        Optional<Vehicle> v = vehicleRepository.findById(periodCreateDto.getId());
        Vehicle vehicle = new Vehicle();
        if(v.isPresent()){
            vehicle = v.get();
        }
        period.setVehicle(vehicle);
        period.setStartDate(periodCreateDto.getStartDate());
        period.setEndDate(periodCreateDto.getEndDate());

        return period;
    }

    public PeriodCreateDto periodToPeriodCreateDto(Period period) {
        PeriodCreateDto periodCreateDto = new PeriodCreateDto();
        periodCreateDto.setId(period.getId());
        periodCreateDto.setStartDate(period.getStartDate());
        periodCreateDto.setEndDate(period.getEndDate());

        return periodCreateDto;
    }

}
