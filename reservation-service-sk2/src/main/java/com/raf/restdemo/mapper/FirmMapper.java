package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.Firm;
import com.raf.restdemo.domain.VehicleType;
import com.raf.restdemo.dto.firm.FirmCreateDto;
import com.raf.restdemo.dto.firm.FirmDto;
import com.raf.restdemo.dto.firm.FirmFilterPriceDto;
import com.raf.restdemo.dto.firm.FirmUpdateDto;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FirmMapper {

    public FirmDto firmToFirmDto(Firm firm) {
        FirmDto firmDto = new FirmDto();

        firmDto.setId(firm.getId());
        firmDto.setName(firm.getName());
        firmDto.setDescription(firm.getDescription());
        firmDto.setCity(firm.getCity());

        return firmDto;
    }

    public Firm firmCreateDtoToFirm(FirmCreateDto firmCreateDto) {
        Firm firm = new Firm();

        firm.setName(firmCreateDto.getName());
        firm.setDescription(firmCreateDto.getDescription());
        firm.setCity(firmCreateDto.getCity());

        return firm;
    }

    public void updateFirm(Firm firm, FirmUpdateDto firmUpdateDto) {
        if(firmUpdateDto.getName() != null)
            firm.setName(firmUpdateDto.getName());
        if(firmUpdateDto.getDescription() != null)
            firm.setDescription(firmUpdateDto.getDescription());
        if(firmUpdateDto.getCity() != null)
            firm.setCity(firmUpdateDto.getCity());
    }

    public FirmFilterPriceDto makeFilterPrice(Firm firm, VehicleType vehicleType, Date startDate, Date endDate) {
        FirmFilterPriceDto firmFilterPriceDto = new FirmFilterPriceDto();

        firmFilterPriceDto.setFirmId(firm.getId().toString());
        firmFilterPriceDto.setFirmName(firm.getName());
        firmFilterPriceDto.setFirmDescription(firm.getDescription());
        firmFilterPriceDto.setFirmCity(firm.getCity());

        firmFilterPriceDto.setVehicleTypeId(vehicleType.getId().toString());
        firmFilterPriceDto.setVehicleTypeCategory(vehicleType.getCategory());
        firmFilterPriceDto.setVehicleTypePrice(String.valueOf(vehicleType.getPricePerDay()));

        firmFilterPriceDto.setStartDate(startDate);
        firmFilterPriceDto.setEndDate(endDate);

        return firmFilterPriceDto;
    }

}
