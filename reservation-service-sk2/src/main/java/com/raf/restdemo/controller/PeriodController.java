package com.raf.restdemo.controller;

import com.raf.restdemo.dto.firm.FirmCreateDto;
import com.raf.restdemo.dto.firm.FirmDto;
import com.raf.restdemo.dto.period.PeriodCreateDto;
import com.raf.restdemo.security.CheckSecurity;
import com.raf.restdemo.service.PeriodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/periods")
public class PeriodController {


    private final PeriodService periodService;


    public PeriodController(PeriodService periodService) {
        this.periodService = periodService;
    }

    @PostMapping
    public ResponseEntity<PeriodCreateDto> createPeriod(@RequestHeader("Authorization") String authorization, @RequestBody @Valid PeriodCreateDto periodCreateDto){
        return new ResponseEntity<>(periodService.createPeriod(periodCreateDto), HttpStatus.CREATED);
    }

}
