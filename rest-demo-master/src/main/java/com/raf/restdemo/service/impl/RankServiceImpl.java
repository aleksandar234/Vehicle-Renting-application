package com.raf.restdemo.service.impl;

import com.raf.restdemo.domain.old.Client;
import com.raf.restdemo.domain.old.Rank;
import com.raf.restdemo.dto.rank.RankCreateDto;
import com.raf.restdemo.dto.rank.RankDto;
import com.raf.restdemo.dto.rank.RankUpdateDto;
import com.raf.restdemo.exception.NotFoundException;
import com.raf.restdemo.mapper.RankMapper;
import com.raf.restdemo.repository.old.ClientRepository;
import com.raf.restdemo.repository.old.RankRepository;
import com.raf.restdemo.service.RankService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RankServiceImpl implements RankService {


    private static final String rankNotFound = "Rank with given ID not found!";
    private static final String userNotFound = "User with given ID not found!";
    private static final String discountNotFound = "Discount for given user not found";

    private final RankRepository rankRepository;
    private final RankMapper rankMapper;
    private final ClientRepository clientRepository;

    public RankServiceImpl(RankRepository rankRepository, RankMapper rankMapper, ClientRepository clientRepository) {
        this.rankRepository = rankRepository;
        this.rankMapper = rankMapper;
        this.clientRepository = clientRepository;
    }

    @Override
    public Page<RankDto> findAll(Pageable pageable) {
        return rankRepository.findAll(pageable).map(rankMapper::rankToRankDto);
    }

    @Override
    public RankDto createRank(RankCreateDto rankCreateDto) {
        Rank rank = rankMapper.rankCreateDtoToRank(rankCreateDto);
        return rankMapper.rankToRankDto(rankRepository.save(rank));
    }

    @Override
    public RankDto updateRank(Long id, RankUpdateDto rankUpdateDto) {
        Rank rank = null;
        try {
            rank = rankRepository.findById(id).orElseThrow(() -> new NotFoundException(rankNotFound));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        rankMapper.rankUpdateDtoToRank(rank, rankUpdateDto);
        return rankMapper.rankToRankDto(rankRepository.save(rank));
    }

    @Override
    public RankDto findDiscount(String username) {
        Client client = clientRepository.findClientByUsername(username).orElseThrow(() -> new NotFoundException(userNotFound));
        int numberOfRentedVehicles = client.getRentedVehicles();
        Rank rank;
        if(numberOfRentedVehicles < 14) {
            rank = rankRepository.findRankByName("Silver");
        } else if(numberOfRentedVehicles > 14 && numberOfRentedVehicles <= 30) {
            rank = rankRepository.findRankByName("Gold");
        } else {
            rank = rankRepository.findRankByName("Platinum");
        }
        return rankMapper.rankToRankDto(rank);
    }

    @Override
    public void deleteRank(Long id) {
        rankRepository.deleteById(id);
    }
}
