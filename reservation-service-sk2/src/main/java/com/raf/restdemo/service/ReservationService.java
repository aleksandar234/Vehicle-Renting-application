package com.raf.restdemo.service;

import com.raf.restdemo.dto.reservation.ReservationCreateDto;
import com.raf.restdemo.dto.reservation.ReservationDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationService {

    List<ReservationDto> getReservationsByFirm(String authorization, Pageable pageable);
    List<ReservationDto> getReservations(String authorization, Pageable pageable);
    ReservationDto makeReservation(ReservationCreateDto reservationCreateDto, String authorization);

    void deleteReservationManager(Long id, String authorization);
    void deleteReservation(Long id, String authorization);

}
