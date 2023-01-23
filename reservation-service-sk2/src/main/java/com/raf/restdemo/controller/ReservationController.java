package com.raf.restdemo.controller;

import com.raf.restdemo.dto.reservation.ReservationCreateDto;
import com.raf.restdemo.dto.reservation.ReservationDto;
import com.raf.restdemo.security.CheckSecurity;
import com.raf.restdemo.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_USER"})
    public ResponseEntity<ReservationDto> makeReservation(@RequestHeader("Authorization") String authorization, @RequestBody ReservationCreateDto reservationCreateDto){
        return new ResponseEntity<>(reservationService.makeReservation(reservationCreateDto, authorization), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
//    @CheckSecurity(roles = {"CLIENT"})
    public ResponseEntity<HttpStatus> deleteReservation(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        reservationService.deleteReservation(id, authorization);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/manager/{id}")
//    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<HttpStatus> deleteReservationManager(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        reservationService.deleteReservationManager(id, authorization);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
