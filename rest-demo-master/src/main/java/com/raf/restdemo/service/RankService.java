package com.raf.restdemo.service;

import com.raf.restdemo.dto.rank.RankCreateDto;
import com.raf.restdemo.dto.rank.RankDto;
import com.raf.restdemo.dto.rank.RankUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RankService {

    Page<RankDto> findAll(Pageable pageable);

    RankDto createRank(RankCreateDto rankCreateDto);
    RankDto updateRank(Long id, RankUpdateDto rankUpdateDto);

    RankDto findDiscount(String username);

    void deleteRank(Long id);

}
