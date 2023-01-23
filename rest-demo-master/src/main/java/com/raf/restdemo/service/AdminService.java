package com.raf.restdemo.service;

import com.raf.restdemo.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.function.LongFunction;


public interface AdminService {

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    ClientDto addClient(ClientCreateDto clientCreateDto);

    ManagerDto addManager(ManagerCreateDto managerCreateDto);

    ResponseEntity<String> modifyAdmin(AdminCreateDto adminCreateDto, String token);

    void banClient(Long id);

    void banManager(Long id);

    void unbanClient(Long id);

    void unbanManager(Long id);

}
