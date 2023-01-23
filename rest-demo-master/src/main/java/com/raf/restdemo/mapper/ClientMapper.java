package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.old.Client;
import com.raf.restdemo.domain.old.Role;
import com.raf.restdemo.dto.ClientCreateDto;
import com.raf.restdemo.dto.ClientDto;
import com.raf.restdemo.repository.old.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    private RoleRepository roleRepository;

    public ClientMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ClientDto clientToClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setEmail(client.getEmail());
        clientDto.setUsername(client.getUsername());
        clientDto.setRentedVehicles(client.getRentedVehicles());
        return clientDto;
    }

    public Client clientCreateDtoToClient(ClientCreateDto clientCreateDto) {
        Client client = new Client();
        client.setUsername(clientCreateDto.getUsername());
        client.setPassword(clientCreateDto.getPassword());
        client.setEmail(clientCreateDto.getEmail());
        client.setPhoneNumber(clientCreateDto.getPhoneNumber());
        client.setDateOfBirth(clientCreateDto.getDateOfBirth());
        client.setFirstName(clientCreateDto.getFirstName());
        client.setLastName(clientCreateDto.getLastName());
        client.setRentedVehicles(clientCreateDto.getRentedVehicles());
//        Role role = new Role("ROLE_USER", "User role");
//        client.setRole(role);
        // Ne pravimo svaki put Role-u da ne bi imali pod rolama 10 R0LE_USERA
        // Vec nalazimo u tabeli role rolu koja nam odgovara i onda je setujemo za tog usera
        client.setRole(roleRepository.findRoleByName("ROLE_USER").get());
        client.setBanned("UNBANNED");
        return client;
    }

}
