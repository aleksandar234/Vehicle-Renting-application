package com.raf.restdemo.service;

import com.raf.restdemo.dto.firm.FirmCreateDto;
import com.raf.restdemo.dto.firm.FirmDto;
import com.raf.restdemo.dto.period.PeriodCreateDto;

public interface PeriodService {

    PeriodCreateDto createPeriod(PeriodCreateDto periodCreateDto);

}
