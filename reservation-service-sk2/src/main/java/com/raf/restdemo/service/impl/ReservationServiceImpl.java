package com.raf.restdemo.service.impl;

import com.raf.restdemo.domain.*;
import com.raf.restdemo.dto.reservation.ReservationCreateDto;
import com.raf.restdemo.dto.reservation.ReservationDto;
import com.raf.restdemo.dto.user.*;
import com.raf.restdemo.mapper.ReservationMapper;
import com.raf.restdemo.repository.*;
import com.raf.restdemo.security.TokenService;
import com.raf.restdemo.service.ReservationService;
import io.jsonwebtoken.Claims;
import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private static final String firmNotFound = "Firm with given id not found!";
    private static final String vehicleTypeNotFound = "Room type with given id not found!";
    private static final String reservationNotFound = "Reservation with given id not found!";


    private final TokenService tokenService;
    private final RestTemplate clientServiceRestTemplate;
    private final ReservationRepository reservationRepository;
    private final FirmRepository firmRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final PeriodRepository periodRepository;
    private final VehicleRepository vehicleRepository;
    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(TokenService tokenService, RestTemplate clientServiceRestTemplate, ReservationRepository reservationRepository, FirmRepository firmRepository, VehicleTypeRepository vehicleTypeRepository, PeriodRepository periodRepository, VehicleRepository vehicleRepository, ReservationMapper reservationMapper) {
        this.tokenService = tokenService;
        this.clientServiceRestTemplate = clientServiceRestTemplate;
        this.reservationRepository = reservationRepository;
        this.firmRepository = firmRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.periodRepository = periodRepository;
        this.vehicleRepository = vehicleRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public ReservationDto makeReservation(ReservationCreateDto reservationCreateDto, String authorization) {
        try {
            Reservation reservation = reservationMapper.reservationCreateDtoToReservation(reservationCreateDto);
            Firm firm = firmRepository.findById(reservationCreateDto.getFirmId()).orElseThrow(() -> new NotFoundException(firmNotFound));
            Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
            String username = claims.get("username", String.class);
            String email = claims.get("email", String.class);
            reservation.setUsername(username);
            reservation.setFirm(firm);

            VehicleType vehicleType = vehicleTypeRepository.findById(reservationCreateDto.getVehicleTypeId()).orElseThrow(() -> new NotFoundException(vehicleTypeNotFound));
            boolean vehicleFound = false;
            for(Vehicle vehicle: vehicleType.getVehicles()) {
                List<Period> p1 = periodRepository.findPeriodByStartDateBeforeAndEndDateAfterAndVehicleId(reservationCreateDto.getStartDate(), reservationCreateDto.getStartDate(), vehicle.getId());
                List<Period> p2 = periodRepository.findPeriodByStartDateBeforeAndEndDateAfterAndVehicleId(reservationCreateDto.getEndDate(), reservationCreateDto.getEndDate(), vehicle.getId());
                List<Period> p3 = periodRepository.findPeriodByStartDateAfterAndEndDateBeforeAndVehicleId(reservationCreateDto.getStartDate(), reservationCreateDto.getEndDate(), vehicle.getId());
                List<Period> p4 = periodRepository.findPeriodByStartDateAndEndDateAndVehicleId(reservationCreateDto.getStartDate(), reservationCreateDto.getEndDate(), vehicle.getId());

                if(p1.isEmpty() && p2.isEmpty() && p3.isEmpty() && p4.isEmpty()) {
                    Period period = new Period();
                    period.setStartDate(reservationCreateDto.getStartDate());
                    period.setEndDate(reservationCreateDto.getEndDate());
                    period.setVehicle(vehicle);

                    vehicle.getPeriods().add(period);
                    reservation.setVehicle(vehicle);
                    vehicleFound = true;

                    break;
                }

            }
            if(!vehicleFound) {
                throw new NotFoundException("Reservation already taken!");
            }

            double price = getDaysBetween(reservationCreateDto.getStartDate(), reservationCreateDto.getEndDate()) * vehicleType.getPricePerDay();

            // sad treba da excangujem sa ovim servicom za discount prosledim id clientUsera koji gore imam
            // da vidim koja je divizija, i na osnovu toga da sracunam koliki mu je popust

            HttpHeaders httpHeaders = new HttpHeaders();
            System.out.println(authorization.split(" ")[1]);
            httpHeaders.add("Authorization", "Bearer " + authorization.split(" ")[1]);
            ResponseEntity<RankDto> rankDto = clientServiceRestTemplate.exchange("/ranks/discount/" + username, HttpMethod.GET, new HttpEntity<>(httpHeaders), RankDto.class);
            if(rankDto.getBody().getDiscount() != 0) {
                price *= (1 - rankDto.getBody().getDiscount());
            }
            reservation.setPrice(price);

            ResponseEntity<ClientDto> clientDto = clientServiceRestTemplate.exchange("/client/" + username, HttpMethod.GET, new HttpEntity<>(httpHeaders), ClientDto.class);

            ClientUpdateDto clientUpdateDto = new ClientUpdateDto();
            clientUpdateDto.setRentedVehicles(clientDto.getBody().getRentedVehicles() + 1);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + authorization.split(" ")[1]);
            HttpEntity httpEntity = new HttpEntity(clientUpdateDto, headers);
            ResponseEntity<ClientUpdateDto> response = clientServiceRestTemplate.exchange("/client/modifyClientVehicles", HttpMethod.PUT, httpEntity, ClientUpdateDto.class);

            return reservationMapper.reservationToReservationDto(reservationRepository.save(reservation));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    private double getDaysBetween(Date startDate, Date endDate) {
        long daysDifference = endDate.getTime() - startDate.getTime();
        return (int) TimeUnit.DAYS.convert(daysDifference, TimeUnit.MILLISECONDS);
    }


    @Override
    public List<ReservationDto> getReservationsByFirm(String authorization, Pageable pageable) {
        return null;
    }

    @Override
    public List<ReservationDto> getReservations(String authorization, Pageable pageable) {
        return null;
    }


    @Override
    public void deleteReservationManager(Long id, String authorization) {
        try {
            Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
//            Long managerId = claims.get("id", Long.class);
            String managerUsername = claims.get("username", String.class);
            String managerEmail = claims.get("email", String.class);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + authorization.split(" ")[1]);

            ResponseEntity<ManagerDto> managerDto = null;
            managerDto = clientServiceRestTemplate.exchange("/manager/" + managerEmail,HttpMethod.GET, new HttpEntity<>(httpHeaders), ManagerDto.class);
            String firmName = managerDto.getBody().getNameOfFirm();
            Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(reservationNotFound));
            String username = reservation.getUsername();

            if(reservation.getFirm().getName().equals(firmName)) {
                Period periodToDelete = periodRepository.findPeriodByStartDateAndEndDateAndVehicleId(reservation.getStartDate(), reservation.getEndDate(), reservation.getVehicle().getId()).get(0);
                reservation.getVehicle().getPeriods().remove(periodToDelete);

                periodRepository.delete(periodToDelete);
                vehicleRepository.save(reservation.getVehicle());
                System.out.println(reservationRepository.findAll().size());
                reservationRepository.delete(reservation);
                System.out.println(reservationRepository.findAll().size());


                ResponseEntity<ClientDto> clientDto = clientServiceRestTemplate.exchange("/client/" + username, HttpMethod.GET, new HttpEntity<>(httpHeaders), ClientDto.class);

                ManagerDeleteReservationDto managerDeleteReservationDto = new ManagerDeleteReservationDto();
                managerDeleteReservationDto.setRentedVehicles(clientDto.getBody().getRentedVehicles() - 1);
                managerDeleteReservationDto.setClientUsername(username);
                System.out.println(managerDeleteReservationDto.getClientUsername());

                HttpEntity httpEntity = new HttpEntity(managerDeleteReservationDto);
                clientServiceRestTemplate.exchange("/client/modifyClientVehiclesByManager", HttpMethod.PUT, httpEntity, ManagerDeleteReservationDto.class);

            }


        } catch (NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteReservation(Long id, String authorization) {
        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        String username = claims.get("username", String.class);
        String email = claims.get("email", String.class);

        try {
            Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(reservationNotFound));
            if(reservation.getUsername().equals(reservation.getUsername())) {
                Period periodToDelete = periodRepository.findPeriodByStartDateAndEndDateAndVehicleId(reservation.getStartDate(), reservation.getEndDate(), reservation.getVehicle().getId()).get(0);
                System.out.println(periodToDelete.toString());
                reservation.getVehicle().getPeriods().remove(periodToDelete);
                periodRepository.delete(periodToDelete);
                vehicleRepository.save(reservation.getVehicle());
                reservationRepository.delete(reservation);

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Authorization", "Bearer " + authorization.split(" ")[1]);

                ResponseEntity<ClientDto> clientDto = clientServiceRestTemplate.exchange("/client/" + username, HttpMethod.GET, new HttpEntity<>(httpHeaders), ClientDto.class);

                ClientUpdateDto clientUpdateDto = new ClientUpdateDto();
                clientUpdateDto.setRentedVehicles(clientDto.getBody().getRentedVehicles() - 1);

                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", "Bearer " + authorization.split(" ")[1]);
                HttpEntity httpEntity = new HttpEntity(clientUpdateDto, headers);
                clientServiceRestTemplate.exchange("/client/modifyClientVehicles", HttpMethod.PUT, httpEntity, ClientUpdateDto.class);
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
