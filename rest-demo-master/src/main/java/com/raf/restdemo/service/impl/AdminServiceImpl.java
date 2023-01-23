package com.raf.restdemo.service.impl;

import com.raf.restdemo.domain.old.Admin;
import com.raf.restdemo.domain.old.Client;
import com.raf.restdemo.domain.old.Manager;
import com.raf.restdemo.dto.*;
import com.raf.restdemo.exception.NotFoundException;
import com.raf.restdemo.mapper.ClientMapper;
import com.raf.restdemo.mapper.ManagerMapper;
import com.raf.restdemo.repository.old.AdminRepository;
import com.raf.restdemo.repository.old.ClientRepository;
import com.raf.restdemo.repository.old.ManagerRepository;
import com.raf.restdemo.secutiry.service.TokenService;
import com.raf.restdemo.service.AdminService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private static final String notFoundMessageId = "User with given ID not found!";

    private final AdminRepository adminRepository;
    private final TokenService tokenService;
    private final ClientMapper clientMapper;
    private final ManagerMapper managerMapper;
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;

    public AdminServiceImpl(AdminRepository adminRepository, TokenService tokenService, ClientMapper clientMapper, ManagerMapper managerMapper, ClientRepository clientRepository, ManagerRepository managerRepository) {
        this.adminRepository = adminRepository;
        this.tokenService = tokenService;
        this.clientMapper = clientMapper;
        this.managerMapper = managerMapper;
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        Admin admin = adminRepository
                .findAdminByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("username", admin.getUsername());
        claims.put("email", admin.getEmail());
        claims.put("id", admin.getId());
        claims.put("role", admin.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public ClientDto addClient(ClientCreateDto clientCreateDto) {
        Client client = clientMapper.clientCreateDtoToClient(clientCreateDto);
        clientRepository.save(client);
        return clientMapper.clientToClientDto(client);
    }


    @Override
    public ManagerDto addManager(ManagerCreateDto managerCreateDto) {
        Manager manager = managerMapper.managerCreateDtoToManager(managerCreateDto);
        managerRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public ResponseEntity<String> modifyAdmin(AdminCreateDto adminCreateDto, String token) {
        Claims claims = this.tokenService.parseToken(token.substring(7));
        String email = (String) claims.get("email");
        System.out.println("Ovo je email:" + email);


        if(this.adminRepository.findAdminByEmail(email).isPresent()) {
            Admin adminD = this.adminRepository.findAdminByEmail(email).get();
            adminRepository.delete(adminD);
            if(adminCreateDto.getEmail() != null) {
                if(this.adminRepository.findAdminByEmail(adminCreateDto.getEmail()).isPresent())
                    return ResponseEntity.status(422).body("EMAIL_TAKEN");
                adminD.setEmail(adminCreateDto.getEmail());
            }

            if(adminCreateDto.getUsername() != null) {
                if(this.adminRepository.findAdminByUsername(adminCreateDto.getUsername()).isPresent())
                    return ResponseEntity.status(422).body("USERNAME_TAKEN");
                adminD.setUsername(adminCreateDto.getUsername());
            }

            if(adminCreateDto.getFirstName() != null) {
                adminD.setFirstName(adminCreateDto.getFirstName());
            }
            if(adminCreateDto.getLastName() != null) {
                adminD.setLastName(adminCreateDto.getLastName());
            }
            if(adminCreateDto.getPassword() != null) {
                adminD.setPassword(adminCreateDto.getPassword());
            }
            if(adminCreateDto.getPhoneNumber() != null) {
                adminD.setPhoneNumber(adminCreateDto.getPhoneNumber());
            }
            if(adminCreateDto.getDateOfBirth() != null) {
                adminD.setDateOfBirth(adminCreateDto.getDateOfBirth());
            }

            this.adminRepository.save(adminD);
            return ResponseEntity.ok().body("EDIT_SUCCESS");
        }

        return ResponseEntity.status(401).build();
    }

    @Override
    public void banClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessageId));
        client.setBanned("Banned");
        clientRepository.save(client);
    }

    @Override
    public void banManager(Long id) {
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessageId));
        manager.setBanned("Banned");
        managerRepository.save(manager);
    }

    @Override
    public void unbanClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessageId));
        client.setBanned("UnBanned");
        clientRepository.save(client);
    }

    @Override
    public void unbanManager(Long id) {
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessageId));
        manager.setBanned("UnBanned");
        managerRepository.save(manager);
    }

}
