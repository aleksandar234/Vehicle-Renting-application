package com.raf.restdemo.service;

import com.raf.restdemo.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ClientService {

    Page<ClientDto> findAll(Pageable pageable);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    ResponseEntity<String> modifyClient(ClientCreateDto clientCreateDto, String token);

    ClientDto getClientByUsername(String username);

    ResponseEntity<String> modifyClientVehicles(ClientUpdateDto clientUpdateDto, String token);

    ResponseEntity<String> modifyClientVehiclesByManager(ManagerDeleteReservationDto managerDeleteReservationDto);

}
