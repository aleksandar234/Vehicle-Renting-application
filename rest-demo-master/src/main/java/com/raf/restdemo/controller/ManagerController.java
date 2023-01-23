package com.raf.restdemo.controller;

import com.raf.restdemo.dto.*;
import com.raf.restdemo.secutiry.CheckSecurity;
import com.raf.restdemo.service.ManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<Page<ManagerDto>> getAllManagers(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(managerService.findAll(pageable), HttpStatus.OK);
    }


    @PostMapping("/modifyManager")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<String> modifyManager(@RequestHeader("Authorization") String authorization,
                                                @RequestBody @Valid ManagerCreateDto managerCreateDto) {
        return this.managerService.modifyManager(managerCreateDto, authorization);
    }

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginManager(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(managerService.login(tokenRequestDto), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<ManagerDto> getManagerByEmail(@RequestHeader("Authorization") String authorization, @PathVariable("email") String email){
        return new ResponseEntity<>(managerService.getManagerByManagerEmail(email), HttpStatus.OK);
    }


}
