package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.Reservation;
import com.raf.restdemo.dto.reservation.ReservationCreateDto;
import com.raf.restdemo.dto.reservation.ReservationDto;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public ReservationDto reservationToReservationDto(Reservation reservation){
        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setId(reservation.getId());
        reservationDto.setVehicleTypeId(reservation.getVehicle().getVehicleType().getId());
        reservationDto.setVehicleTypeCategory(reservation.getVehicle().getVehicleType().getCategory());
        reservationDto.setFirmName(reservation.getFirm().getName());
        reservationDto.setUsername(reservation.getUsername());
        reservationDto.setPrice(reservation.getPrice());
        reservationDto.setStartDate(reservation.getStartDate());
        reservationDto.setEndDate(reservation.getEndDate());

        return reservationDto;
    }

    public Reservation reservationCreateDtoToReservation(ReservationCreateDto reservationCreateDto){
        Reservation reservation = new Reservation();

        reservation.setStartDate(reservationCreateDto.getStartDate());
        reservation.setEndDate(reservationCreateDto.getEndDate());

        return reservation;
    }

}
