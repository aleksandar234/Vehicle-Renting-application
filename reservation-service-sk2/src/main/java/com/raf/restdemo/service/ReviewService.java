package com.raf.restdemo.service;

import com.raf.restdemo.dto.firm.BestFirmDto;
import com.raf.restdemo.dto.firm.FirmDto;
import com.raf.restdemo.dto.review.ReviewCreateDto;
import com.raf.restdemo.dto.review.ReviewDto;
import com.raf.restdemo.dto.review.ReviewFilterDto;
import com.raf.restdemo.dto.review.ReviewUpdateDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(ReviewCreateDto reviewCreateDto, String authorization);
    ReviewDto updateReview(ReviewUpdateDto reviewUpdateDto, Long id, String authorization);
    void deleteReview(Long id, String authorization);

    List<ReviewDto> filterReviews(ReviewFilterDto reviewFilterDto, Pageable pageable);

    List<BestFirmDto> bestFirm();

}
