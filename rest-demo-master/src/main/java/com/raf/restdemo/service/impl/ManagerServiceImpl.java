package com.raf.restdemo.service.impl;

import com.raf.restdemo.domain.old.Manager;
import com.raf.restdemo.dto.ManagerCreateDto;
import com.raf.restdemo.dto.ManagerDto;
import com.raf.restdemo.dto.TokenRequestDto;
import com.raf.restdemo.dto.TokenResponseDto;
import com.raf.restdemo.exception.NotFoundException;
import com.raf.restdemo.mapper.ManagerMapper;
import com.raf.restdemo.repository.old.ManagerRepository;
import com.raf.restdemo.secutiry.service.TokenService;
import com.raf.restdemo.service.ManagerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    private static final String bannedUser = "You have been banned from Admin";

    private ManagerRepository managerRepository;
    private ManagerMapper managerMapper;
    private TokenService tokenService;

    public ManagerServiceImpl(ManagerRepository managerRepository, ManagerMapper managerMapper, TokenService tokenService) {
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
        this.tokenService = tokenService;
    }

    @Override
    public Page<ManagerDto> findAll(Pageable pageable) {
        return managerRepository.findAll(pageable)
                .map(managerMapper::managerToManagerDto);
    }


    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        //Try to find active user for specified credentials
        Manager manager = managerRepository
                .findManagerByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        if(manager.getBanned().equals("Banned")) {
            try {
                throw new Exception(bannedUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("username", manager.getUsername());
        claims.put("email", manager.getEmail());
        claims.put("id", manager.getId());
        claims.put("role", manager.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public ResponseEntity<String> modifyManager(ManagerCreateDto managerCreateDto, String token) {
        Claims claims = this.tokenService.parseToken(token.substring(7));
        String email = (String) claims.get("email");
        System.out.println("Ovo je email:" + email);


        if(this.managerRepository.findManagerByEmail(email).isPresent()) {
            Manager manager = this.managerRepository.findManagerByEmail(email).get();
            managerRepository.delete(manager);
            if(managerCreateDto.getEmail() != null) {
                if(this.managerRepository.findManagerByEmail(managerCreateDto.getEmail()).isPresent())
                    return ResponseEntity.status(422).body("EMAIL_TAKEN");
                manager.setEmail(managerCreateDto.getEmail());
            }

            if(managerCreateDto.getUsername() != null) {
                if(this.managerRepository.findManagerByUsername(managerCreateDto.getUsername()).isPresent())
                    return ResponseEntity.status(422).body("USERNAME_TAKEN");
                manager.setUsername(managerCreateDto.getUsername());
            }

            if(managerCreateDto.getFirstName() != null) {
                manager.setFirstName(managerCreateDto.getFirstName());
            }
            if(managerCreateDto.getLastName() != null) {
                manager.setLastName(managerCreateDto.getLastName());
            }
            if(managerCreateDto.getPassword() != null) {
                manager.setPassword(managerCreateDto.getPassword());
            }
            if(managerCreateDto.getPhoneNumber() != null) {
                manager.setPhoneNumber(managerCreateDto.getPhoneNumber());
            }
            if(managerCreateDto.getDateOfBirth() != null) {
                manager.setDateOfBirth(managerCreateDto.getDateOfBirth());
            }
            if(managerCreateDto.getDateOfHire() != null) {
                manager.setDateOfBirth(managerCreateDto.getDateOfHire());
            }

            this.managerRepository.save(manager);
            return ResponseEntity.ok().body("EDIT_SUCCESS");
        }

        return ResponseEntity.status(401).build();
    }

    @Override
    public ManagerDto getManagerByManagerEmail(String email) {
        Manager manager = managerRepository.findManagerByEmail(email).orElseThrow(() -> new NotFoundException("notFoundMessageId"));
        return managerMapper.managerToManagerDto(manager);
    }

}
