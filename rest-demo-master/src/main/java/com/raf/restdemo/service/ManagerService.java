package com.raf.restdemo.service;

import com.raf.restdemo.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ManagerService {

    Page<ManagerDto> findAll(Pageable pageable);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    ResponseEntity<String> modifyManager(ManagerCreateDto managerCreateDto, String token);

    ManagerDto getManagerByManagerEmail(String email);

}
