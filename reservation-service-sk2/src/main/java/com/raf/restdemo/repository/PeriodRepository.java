package com.raf.restdemo.repository;

import com.raf.restdemo.domain.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {

    List<Period> findPeriodByStartDateBeforeAndEndDateAfterAndVehicleId(Date startDate, Date endDate, Long vehicleId);
    List<Period> findPeriodByStartDateAfterAndEndDateBeforeAndVehicleId(Date startDate, Date endDate, Long vehicleId);
    List<Period> findPeriodByStartDateAndEndDateAndVehicleId(Date startDate, Date endDate, Long vehicleId);

//    List<Period> findPeriodBetweenStartDateAndEndDateAndVehicleId(Date startDate, Date endDate, Long vehicleId);

}
