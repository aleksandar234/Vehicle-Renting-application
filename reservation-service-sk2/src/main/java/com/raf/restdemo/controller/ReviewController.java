package com.raf.restdemo.controller;

import com.raf.restdemo.dto.firm.BestFirmDto;
import com.raf.restdemo.dto.review.ReviewCreateDto;
import com.raf.restdemo.dto.review.ReviewDto;
import com.raf.restdemo.dto.review.ReviewFilterDto;
import com.raf.restdemo.dto.review.ReviewUpdateDto;
import com.raf.restdemo.security.CheckSecurity;
import com.raf.restdemo.service.ReviewService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<ReviewDto> createReview(@RequestHeader("Authorization") String authorization, @RequestBody @Valid ReviewCreateDto reviewCreateDto){
        return new ResponseEntity<>(reviewService.createReview(reviewCreateDto, authorization), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id, @RequestBody ReviewUpdateDto reviewUpdateDto){
        return new ResponseEntity<>(reviewService.updateReview(reviewUpdateDto, id, authorization), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReview(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        reviewService.deleteReview(id, authorization);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ReviewDto>> filterReviews(@RequestHeader("Authorization") String authorization, @RequestBody ReviewFilterDto reviewFilterDto){
        return new ResponseEntity<>(reviewService.filterReviews(reviewFilterDto, PageRequest.of(0, 20)), HttpStatus.OK);
    }

    @GetMapping("/bestFirm")
    public ResponseEntity<List<BestFirmDto>> bestFirm(@RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(reviewService.bestFirm(), HttpStatus.OK);
    }
//
//    @GetMapping
//    public ResponseEntity<List<ReviewDto>> getClientReviews(@RequestHeader("Authorization") String authorization){
//        return new ResponseEntity<>(reviewService.getClientReviews(authorization), HttpStatus.OK);
//    }

}
