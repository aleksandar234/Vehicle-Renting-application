package com.raf.restdemo.repository;

import com.raf.restdemo.domain.Firm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FirmRepository extends JpaRepository<Firm, Long> {

    Page<Firm> findFirmByName(String name, Pageable pageable);
    Page<Firm> findFirmByCity(String city, Pageable pageable);
    Page<Firm> findFirmByCityAndName(String city, String name, Pageable pageable);

    Optional<Firm> findFirmByName(String name);

}
