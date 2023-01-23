package com.raf.restdemo.repository.old;

import com.raf.restdemo.domain.old.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {

//    @Query(value = "SELECT * from ranks r where r.reservation_limit <= :num_of_reservation order by r.reservation_limit desc limit 1", nativeQuery = true)
//    Optional<Rank> findRankByNumberOfReservation(@Param("num_of_reservation") int numOfReservation);

    Rank findRankByName(String name);


}
