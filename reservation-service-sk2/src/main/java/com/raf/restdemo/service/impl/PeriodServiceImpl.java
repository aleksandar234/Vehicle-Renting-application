package com.raf.restdemo.service.impl;

import com.raf.restdemo.domain.Period;
import com.raf.restdemo.dto.period.PeriodCreateDto;
import com.raf.restdemo.mapper.PeriodMapper;
import com.raf.restdemo.repository.PeriodRepository;
import com.raf.restdemo.service.PeriodService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PeriodServiceImpl implements PeriodService {

    private final PeriodRepository periodRepository;
    private final PeriodMapper periodMapper;

    public PeriodServiceImpl(PeriodRepository periodRepository, PeriodMapper periodMapper) {
        this.periodRepository = periodRepository;
        this.periodMapper = periodMapper;
    }


    @Override
    public PeriodCreateDto createPeriod(PeriodCreateDto periodCreateDto) {
        Period period = periodMapper.periodCreateDtoToPeriod(periodCreateDto);
        return periodMapper.periodToPeriodCreateDto(periodRepository.save(period));
    }
}
