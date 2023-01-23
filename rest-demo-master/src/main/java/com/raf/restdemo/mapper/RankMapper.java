package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.old.Rank;
import com.raf.restdemo.dto.rank.RankCreateDto;
import com.raf.restdemo.dto.rank.RankDto;
import com.raf.restdemo.dto.rank.RankUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class RankMapper {

    public RankDto rankToRankDto(Rank rank) {
        RankDto rankDto = new RankDto();

        rankDto.setId(rank.getId());
        rankDto.setName(rank.getName());
        rankDto.setReservationLimit(rank.getReservationLimit());
        rankDto.setDiscount(rank.getDiscount());

        return rankDto;
    }

    public Rank rankCreateDtoToRank(RankCreateDto rankCreateDto) {
        Rank rank = new Rank();

        rank.setName(rankCreateDto.getName());
        rank.setReservationLimit(rankCreateDto.getReservationLimit());
        rank.setDiscount(rankCreateDto.getDiscount());

        return rank;
    }

    public void rankUpdateDtoToRank(Rank rank, RankUpdateDto rankUpdateDto) {
        if (rankUpdateDto.getName() != null)
            rank.setName(rankUpdateDto.getName());

        if (rankUpdateDto.getReservationLimit() != 0)
            rank.setReservationLimit(rankUpdateDto.getReservationLimit());

        if (rankUpdateDto.getDiscount() != 0)
            rank.setDiscount(rankUpdateDto.getDiscount());
    }

}
