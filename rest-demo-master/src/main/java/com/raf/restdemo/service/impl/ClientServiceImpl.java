package com.raf.restdemo.service.impl;

import com.raf.restdemo.domain.old.Client;
import com.raf.restdemo.dto.*;
import com.raf.restdemo.exception.NotFoundException;
import com.raf.restdemo.mapper.ClientMapper;
import com.raf.restdemo.repository.old.ClientRepository;
import com.raf.restdemo.secutiry.service.TokenService;
import com.raf.restdemo.service.ClientService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private static final String bannedUser = "You have been banned from Admin";

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    private TokenService tokenService;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper, TokenService tokenService) {
        this.clientRepository = clientRepository;
        this.tokenService = tokenService;
        this.clientMapper = clientMapper;
    }

    @Override
    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(clientMapper::clientToClientDto);
    }



    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        //Try to find active user for specified credentials
        Client client = clientRepository
                .findClientByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        if(client.getBanned().equals("Banned")) {
            throw new NotFoundException(bannedUser);
        }
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("username", client.getUsername());
        claims.put("email", client.getEmail());
        claims.put("id", client.getId());
        claims.put("role", client.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public ResponseEntity<String> modifyClient(ClientCreateDto clientCreateDto, String token) {
        Claims claims = this.tokenService.parseToken(token.substring(7));
        String email = (String) claims.get("email");
        System.out.println("Ovo je email:" + email);

        if(this.clientRepository.findClientByEmail(email).isPresent()) {
            Client client = this.clientRepository.findClientByEmail(email).get();
            clientRepository.delete(client);
            if(clientCreateDto.getEmail() != null) {
                if(this.clientRepository.findClientByEmail(clientCreateDto.getEmail()).isPresent())
                    return ResponseEntity.status(422).body("EMAIL_TAKEN");
                client.setEmail(clientCreateDto.getEmail());
            }

            if(clientCreateDto.getUsername() != null) {
                if(this.clientRepository.findClientByEmail(clientCreateDto.getEmail()).isPresent())
                    return ResponseEntity.status(422).body("USERNAME_TAKEN");
                client.setUsername(clientCreateDto.getUsername());
            }

            if(clientCreateDto.getFirstName() != null) {
                client.setFirstName(clientCreateDto.getFirstName());
            }
            if(clientCreateDto.getLastName() != null) {
                client.setLastName(clientCreateDto.getLastName());
            }
            if(clientCreateDto.getPassword() != null) {
                client.setPassword(clientCreateDto.getPassword());
            }
            if(clientCreateDto.getPhoneNumber() != null) {
                client.setPhoneNumber(clientCreateDto.getPhoneNumber());
            }
            if(clientCreateDto.getDateOfBirth() != null) {
                client.setDateOfBirth(clientCreateDto.getDateOfBirth());
            }
            if(clientCreateDto.getRentedVehicles() != 0) {
                client.setRentedVehicles(clientCreateDto.getRentedVehicles());
            }

            this.clientRepository.save(client);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(401).build();
    }

    @Override
    public ClientDto getClientByUsername(String username) {
        Optional<Client> client = clientRepository.findClientByUsername(username);
        return clientMapper.clientToClientDto(client.get());
    }

    @Override
    public ResponseEntity<String> modifyClientVehicles(ClientUpdateDto clientUpdateDto, String token) {
        Claims claims = this.tokenService.parseToken(token.substring(7));
        String email = (String) claims.get("email");
        Client client = this.clientRepository.findClientByEmail(email).get();
        if(clientUpdateDto.getRentedVehicles() != 0) {
            client.setRentedVehicles(clientUpdateDto.getRentedVehicles());
        }
        this.clientRepository.save(client);
        return ResponseEntity.status(200).build();
    }

    @Override
    public ResponseEntity<String> modifyClientVehiclesByManager(ManagerDeleteReservationDto managerDeleteReservationDto) {
        System.out.println("Client username: " + managerDeleteReservationDto.getClientUsername());
        Client client = this.clientRepository.findClientByUsername(managerDeleteReservationDto.getClientUsername()).get();
        if(managerDeleteReservationDto.getRentedVehicles() != 0) {
            client.setRentedVehicles(managerDeleteReservationDto.getRentedVehicles());
        }

        this.clientRepository.save(client);
        return ResponseEntity.status(200).build();
    }

}
