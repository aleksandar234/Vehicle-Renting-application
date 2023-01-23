package com.raf.restdemo.service.impl;

import com.raf.restdemo.domain.Firm;
import com.raf.restdemo.domain.Review;
import com.raf.restdemo.dto.firm.BestFirmDto;
import com.raf.restdemo.dto.firm.FirmDto;
import com.raf.restdemo.dto.review.ReviewCreateDto;
import com.raf.restdemo.dto.review.ReviewDto;
import com.raf.restdemo.dto.review.ReviewFilterDto;
import com.raf.restdemo.dto.review.ReviewUpdateDto;
import com.raf.restdemo.mapper.ReviewMapper;
import com.raf.restdemo.repository.FirmRepository;
import com.raf.restdemo.repository.ReviewRepository;
import com.raf.restdemo.security.TokenService;
import com.raf.restdemo.service.ReviewService;
import io.jsonwebtoken.Claims;
import javassist.NotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import org.javatuples.Pair;
@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private static final String hotelNotFound = "Hotel with given id not found!";
    private static final String reviewNotFound = "Review with given id not found!";
    private static final String privilegeError = "You don't have permission for this action!";


    private final TokenService tokenService;
    private final FirmRepository firmRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(TokenService tokenService, FirmRepository firmRepository, ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.tokenService = tokenService;
        this.firmRepository = firmRepository;
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewDto createReview(ReviewCreateDto reviewCreateDto, String authorization) {
        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        String username = claims.get("username", String.class);
        Review review = reviewMapper.reviewCreateDtoToReview(reviewCreateDto);
        review.setUsername(username);
        try {
            review.setFirm(firmRepository.findById(reviewCreateDto.getFirmId()).orElseThrow(() -> new NotFoundException(hotelNotFound)));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        reviewRepository.save(review);

        return reviewMapper.reviewToReviewDto(review);
    }

    @Override
    public ReviewDto updateReview(ReviewUpdateDto reviewUpdateDto, Long id, String authorization) {
        try {
            Review review = reviewRepository.findById(id).orElseThrow(() -> new NotFoundException(reviewNotFound));

            // U sustini ako ti nisi napravio review ne mozes ni da ga updatujes
            Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
            String username = claims.get("username", String.class);

            if(!review.getUsername().equals(username)){
                throw new NotFoundException(privilegeError);
            }

            reviewMapper.updateReview(review, reviewUpdateDto);
            reviewRepository.save(review);

            return reviewMapper.reviewToReviewDto(review);

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteReview(Long id, String authorization) {
        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        String username = claims.get("username", String.class);

        try {
            Review review = reviewRepository.findById(id).orElseThrow(() -> new NotFoundException(reviewNotFound));

            if(!review.getUsername().equals(username)){
                throw new NotFoundException(privilegeError);
            }

            reviewRepository.delete(review);

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ReviewDto> filterReviews(ReviewFilterDto reviewFilterDto, Pageable pageable) {
        Page<Firm> firms;
        if(reviewFilterDto.getCity() != null && reviewFilterDto.getName() != null) {
            firms = firmRepository.findFirmByCityAndName(reviewFilterDto.getCity(), reviewFilterDto.getName(), pageable);
        } else if(reviewFilterDto.getCity() != null) {
            firms = firmRepository.findFirmByCity(reviewFilterDto.getCity(), pageable);
        } else if(reviewFilterDto.getName() != null) {
            firms = firmRepository.findFirmByName(reviewFilterDto.getName(), pageable);
        } else {
            firms = firmRepository.findAll(pageable);
        }

        List<ReviewDto> reviews = new ArrayList<>();
        for (Firm firm: firms){
            for (Review review: firm.getReviews()){
                reviews.add(reviewMapper.reviewToReviewDto(review));
            }
        }

        return reviews;
    }

    @Override
    public List<BestFirmDto> bestFirm() {
        List<Firm> firms = firmRepository.findAll();
//        List<String> names = new ArrayList<>();
        List<Pair<String, Integer>> listPairs = new ArrayList<>();
        for(Firm firm: firms) {
            String name = firm.getName();
            List<Review> ratings = firm.getReviews();
            int rating = 0;
            for(Review r: ratings) {
                rating += r.getRate();
            }
            int intersection;
            if(rating == 0) intersection = 0;
            else intersection = rating / ratings.size();
            Pair<String, Integer> pair = new Pair<>(name, intersection);
            listPairs.add(pair);
        }

        listPairs.sort(Comparator.comparing(p -> -p.getValue1()));
        List<BestFirmDto> bfd = new ArrayList<>();
        for(Pair pair: listPairs) {
            BestFirmDto bestFirmDto = new BestFirmDto();
            bestFirmDto.setName(pair.getValue0().toString());
            bestFirmDto.setRating((int)pair.getValue1());
            System.out.println(bestFirmDto.getName());
            System.out.println(bestFirmDto.getRating());
            bfd.add(bestFirmDto);
        }
        System.out.println(bfd);
        return bfd;
    }


}
