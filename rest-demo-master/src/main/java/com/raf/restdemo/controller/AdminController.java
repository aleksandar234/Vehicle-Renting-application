package com.raf.restdemo.controller;

import com.raf.restdemo.dto.*;
import com.raf.restdemo.secutiry.CheckSecurity;
import com.raf.restdemo.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginAdmin(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(adminService.login(tokenRequestDto), HttpStatus.OK);
    }

    @PutMapping("/modify-admin")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<String> modifyAdmin(@RequestBody @Valid AdminCreateDto adminCreateDto, @RequestHeader("Authorization") String authorization) {
        return this.adminService.modifyAdmin(adminCreateDto, authorization);
    }

    @PostMapping("/saveClient")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ClientDto> saveClient(@RequestHeader("Authorization") String authorization, @RequestBody @Valid ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(adminService.addClient(clientCreateDto), HttpStatus.CREATED);
    }

    @PostMapping("/saveManager")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ManagerDto> saveManager(@RequestHeader("Authorization") String authorization, @RequestBody @Valid ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(adminService.addManager(managerCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/banClient/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ManagerDto> banClient(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        adminService.banClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/unbanClient/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ManagerDto> unbanClient(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        adminService.unbanClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
