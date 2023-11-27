package com.springboot.movie.app.springbootmovieapprestapi.service;

import com.springboot.movie.app.springbootmovieapprestapi.dto.ReviewDto;

public interface ReviewService {

    ReviewDto createReview(long movieId, ReviewDto reviewDto);



}
