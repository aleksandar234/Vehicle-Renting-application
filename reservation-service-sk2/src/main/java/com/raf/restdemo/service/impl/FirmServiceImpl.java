package com.raf.restdemo.service.impl;

import com.raf.restdemo.domain.Firm;
import com.raf.restdemo.dto.firm.FirmCreateDto;
import com.raf.restdemo.dto.firm.FirmDto;
import com.raf.restdemo.dto.firm.FirmUpdateDto;
import com.raf.restdemo.dto.user.ManagerDto;
import com.raf.restdemo.mapper.FirmMapper;
import com.raf.restdemo.repository.FirmRepository;
import com.raf.restdemo.security.TokenService;
import com.raf.restdemo.service.FirmService;
import io.jsonwebtoken.Claims;
import javassist.NotFoundException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class FirmServiceImpl implements FirmService {

    private static final String firmNotFound = "Firm with given id not found!";
    private static final String firmNameNotFound = "Firm with given name not found!";

    private final FirmMapper firmMapper;
    private final FirmRepository firmRepository;
    private final TokenService tokenService;
    private final RestTemplate managerServiceRestTemplate;

    public FirmServiceImpl(FirmMapper firmMapper, FirmRepository firmRepository, TokenService tokenService, RestTemplate managerServiceRestTemplate) {
        this.firmMapper = firmMapper;
        this.firmRepository = firmRepository;
        this.tokenService = tokenService;
        this.managerServiceRestTemplate = managerServiceRestTemplate;
    }

    @Override
    public FirmDto createFirm(FirmCreateDto firmCreateDto) {
        Firm firm = firmMapper.firmCreateDtoToFirm(firmCreateDto);
        return firmMapper.firmToFirmDto(firmRepository.save(firm));
    }


    @Override
    public FirmDto getFirmById(Long id) {
        try {
            return firmMapper.firmToFirmDto(firmRepository.findById(id).orElseThrow(() -> new NotFoundException(firmNotFound)));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FirmDto updateFirm(Long id, FirmUpdateDto firmUpdateDto) {
        return null;
    }

    @Override
    public void deleteFirm(Long id) {
        firmRepository.deleteById(id);
    }

    @Override
    public List<FirmDto> getAllFirms() {
        return firmRepository.findAll().stream().map(firmMapper::firmToFirmDto).collect(Collectors.toList());
    }

    @Override
    public FirmDto getFirmByManager(String authorization) {
        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        String managerEmail = claims.get("email", String.class);
        System.out.println(managerEmail);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + authorization.split(" ")[1]);
        try {
            ResponseEntity<ManagerDto> manager = managerServiceRestTemplate.exchange("/manager/" + managerEmail, HttpMethod.GET, new HttpEntity<>(httpHeaders), ManagerDto.class);
            System.out.println(manager.getBody().getFirstName());
            Firm firm = firmRepository.findFirmByName(manager.getBody().getNameOfFirm()).orElseThrow(() -> new NotFoundException(firmNameNotFound));
            return firmMapper.firmToFirmDto(firm);
        } catch (Exception e) {
//            managerServiceRestTemplate.get
            e.printStackTrace();
        }
        return null;
    }
}
