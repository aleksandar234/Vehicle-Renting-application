package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.old.Manager;
import com.raf.restdemo.domain.old.Role;
import com.raf.restdemo.dto.ManagerCreateDto;
import com.raf.restdemo.dto.ManagerDto;
import com.raf.restdemo.repository.old.RoleRepository;
import org.springframework.stereotype.Component;


@Component
public class ManagerMapper {

    private RoleRepository roleRepository;

    public ManagerMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ManagerDto managerToManagerDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setEmail(manager.getEmail());
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setUsername(manager.getUsername());
        managerDto.setNameOfFirm(manager.getNameOfFirm());
        managerDto.setDateOfHire(manager.getDateOfHire());
        return managerDto;
    }

    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto) {
        Manager manager = new Manager();
        manager.setUsername(managerCreateDto.getUsername());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setEmail(managerCreateDto.getEmail());
        manager.setPhoneNumber(managerCreateDto.getPhoneNumber());
        manager.setDateOfBirth(managerCreateDto.getDateOfBirth());
        manager.setFirstName(managerCreateDto.getFirstName());
        manager.setLastName(managerCreateDto.getLastName());
        manager.setNameOfFirm(managerCreateDto.getNameOfFirm());
        manager.setDateOfHire(managerCreateDto.getDateOfHire());
//        Role role = new Role("ROLE_MANAGER", "Manager role");
//        manager.setRole(role);
        manager.setRole(roleRepository.findRoleByName("ROLE_MANAGER").get());
        manager.setBanned("UnBanned");
        return manager;
    }

}
