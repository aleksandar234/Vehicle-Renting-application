package com.raf.restdemo.repository;

import com.raf.restdemo.domain.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Page<Reservation> findReservationByFirmName(String firmName, Pageable pageable);
    Page<Reservation> findReservationByUsername(String username, Pageable pageable);
    List<Reservation> findReservationByStartDateBetweenAndSent(Date startDate, Date endDate, boolean sent);

}
