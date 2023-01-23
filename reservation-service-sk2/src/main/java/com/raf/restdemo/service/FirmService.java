package com.raf.restdemo.service;

import com.raf.restdemo.dto.firm.FirmCreateDto;
import com.raf.restdemo.dto.firm.FirmDto;
import com.raf.restdemo.dto.firm.FirmUpdateDto;

import java.util.List;

public interface FirmService {

    FirmDto getFirmById(Long id);

    FirmDto createFirm(FirmCreateDto firmCreateDto);

    FirmDto updateFirm(Long id, FirmUpdateDto firmUpdateDto);

    void deleteFirm(Long id);

    List<FirmDto> getAllFirms();

    FirmDto getFirmByManager(String authorization);

}
