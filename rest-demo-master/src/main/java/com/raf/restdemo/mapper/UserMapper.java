package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.newp.User;
import com.raf.restdemo.domain.old.UserRole;
import com.raf.restdemo.dto.attributes.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserMapper {

    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public User userClientCreateDtoToUser(UserClientCreateDto userClientCreateDto) {
        User user = new User();

        user.setRole(UserRole.USER_CLIENT);
        user.setEnabled(false);

        user.setFirstName(userClientCreateDto.getFirstName());
        user.setLastName(userClientCreateDto.getLastName());
        user.setEmail(userClientCreateDto.getEmail());
        user.setUsername(userClientCreateDto.getUsername());
        user.setPassword(userClientCreateDto.getPassword());
        user.setBirthday(userClientCreateDto.getBirthday());
        user.setPhone(userClientCreateDto.getPhone());

        return user;
    }

    public User userManagerCreateDtoToUser(UserManagerCreateDto userManagerCreateDto) {
        User user = new User();

        user.setRole(UserRole.USER_MANAGER);
        user.setEnabled(false);

        user.setFirstName(userManagerCreateDto.getFirstName());
        user.setLastName(userManagerCreateDto.getLastName());
        user.setEmail(userManagerCreateDto.getEmail());
        user.setUsername(userManagerCreateDto.getUsername());
        user.setPassword(userManagerCreateDto.getPassword());
        user.setBirthday(userManagerCreateDto.getBirthday());
        user.setPhone(userManagerCreateDto.getPhone());

        return user;
    }

    // Ovde necu da cuvam direkto UserClientUpdateDto u bazu jer bih morao da brisem postojeceg usera
    // kojeg zelim da izmenim, nego cu samo da settujem njegove parametre na nove parametre i tjt
    public void updateClientUser(UserClientUpdateDto userClientUpdateDto, User user) {
        if(userClientUpdateDto.getFirstName() != null)
            user.setFirstName(userClientUpdateDto.getFirstName());
        if(userClientUpdateDto.getLastName() != null)
            user.setLastName(userClientUpdateDto.getLastName());
        if(userClientUpdateDto.getEmail() != null)
            user.setEmail(userClientUpdateDto.getEmail());
        if(userClientUpdateDto.getPassword() != null)
            user.setPassword(userClientUpdateDto.getPassword());
        if(userClientUpdateDto.getUsername() != null)
            user.setUsername(userClientUpdateDto.getUsername());
        if(userClientUpdateDto.getPhone() != null)
            user.setPhone(userClientUpdateDto.getPhone());
        if(userClientUpdateDto.getBirthday() != null)
            user.setBirthday(userClientUpdateDto.getBirthday());
        if(userClientUpdateDto.getPassportNumber() != null)
            user.getClientAttribute().setPassportNumber(userClientUpdateDto.getPassportNumber());
    }

    public void updateManagerUser(UserManagerUpdateDto userManagerUpdateDto, User user) {
        if (userManagerUpdateDto.getFirstName() != null)
            user.setFirstName(userManagerUpdateDto.getFirstName());

        if (userManagerUpdateDto.getLastName() != null)
            user.setLastName(userManagerUpdateDto.getLastName());

        if (userManagerUpdateDto.getUsername() != null)
            user.setUsername(userManagerUpdateDto.getUsername());

        if (userManagerUpdateDto.getPassword() != null)
            user.setPassword(userManagerUpdateDto.getPassword());

        if (userManagerUpdateDto.getEmail() != null)
            user.setEmail(userManagerUpdateDto.getEmail());

        if (userManagerUpdateDto.getPhone() != null)
            user.setPhone(userManagerUpdateDto.getPhone());

        if (userManagerUpdateDto.getBirthday() != null)
            user.setBirthday(userManagerUpdateDto.getBirthday());

        if(userManagerUpdateDto.getHotelName() != null) {
            user.getManagerAttribute().setHotelName(userManagerUpdateDto.getHotelName());
            user.getManagerAttribute().setEmploymentDate(new Date());
        }

    }


















}
