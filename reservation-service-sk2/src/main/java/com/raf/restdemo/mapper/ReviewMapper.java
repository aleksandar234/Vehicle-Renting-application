package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.Review;
import com.raf.restdemo.dto.review.ReviewCreateDto;
import com.raf.restdemo.dto.review.ReviewDto;
import com.raf.restdemo.dto.review.ReviewUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public Review reviewCreateDtoToReview(ReviewCreateDto reviewCreateDto){
        Review review = new Review();

        review.setComment(reviewCreateDto.getComment());
        review.setRate(reviewCreateDto.getRate());

        return review;
    }

    public ReviewDto reviewToReviewDto(Review review){
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setUsername(review.getUsername());
        reviewDto.setComment(review.getComment());
        reviewDto.setRate(review.getRate());
        reviewDto.setId(review.getId());

        return reviewDto;
    }


    public void updateReview(Review review, ReviewUpdateDto reviewUpdateDto){
        if (reviewUpdateDto.getComment() != null){
            review.setComment(reviewUpdateDto.getComment());
        }
        if (reviewUpdateDto.getRate() != 0){
            review.setRate(reviewUpdateDto.getRate());
        }
    }


}
