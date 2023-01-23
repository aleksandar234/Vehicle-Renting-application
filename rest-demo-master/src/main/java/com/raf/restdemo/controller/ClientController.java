package com.raf.restdemo.controller;

import com.raf.restdemo.dto.*;
import com.raf.restdemo.secutiry.CheckSecurity;
import com.raf.restdemo.service.ClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @CheckSecurity(roles = {"ROLE_USER"})
    public ResponseEntity<Page<ClientDto>> getAllUsers(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(clientService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_MANAGER"})
    public ResponseEntity<ClientDto> getClientByUsername(@RequestHeader("Authorization") String authorization, @PathVariable("username") String username) {
        return new ResponseEntity<>(clientService.getClientByUsername(username), HttpStatus.OK);
    }


    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginClient(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(clientService.login(tokenRequestDto), HttpStatus.OK);
    }

    @PutMapping("/modifyClient")
    @CheckSecurity(roles = {"ROLE_USER"})
    public ResponseEntity<String> modifyClient(@RequestHeader("Authorization") String authorization,
                                               @RequestBody @Valid ClientCreateDto clientCreateDto) {
        return this.clientService.modifyClient(clientCreateDto, authorization);
    }

    @PutMapping("/modifyClientVehicles")
    public ResponseEntity<String> modifyClientVehicles(@RequestHeader("Authorization") String authorization,
                                               @RequestBody @Valid ClientUpdateDto clientUpdateDto) {
        return this.clientService.modifyClientVehicles(clientUpdateDto, authorization);
    }

    @PutMapping("/modifyClientVehiclesByManager")
    public ResponseEntity<String> modifyClientVehiclesByManager(@RequestBody @Valid ManagerDeleteReservationDto managerDeleteReservationDto) {
        return this.clientService.modifyClientVehiclesByManager(managerDeleteReservationDto);
    }

}
