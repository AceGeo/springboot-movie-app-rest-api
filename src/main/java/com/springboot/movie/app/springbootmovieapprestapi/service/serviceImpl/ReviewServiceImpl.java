package com.springboot.movie.app.springbootmovieapprestapi.service.serviceImpl;

import com.springboot.movie.app.springbootmovieapprestapi.dto.ReviewDto;
import com.springboot.movie.app.springbootmovieapprestapi.entity.Movie;
import com.springboot.movie.app.springbootmovieapprestapi.entity.Review;
import com.springboot.movie.app.springbootmovieapprestapi.exception.ResourceNotFoundException;
import com.springboot.movie.app.springbootmovieapprestapi.repository.MovieRepository;
import com.springboot.movie.app.springbootmovieapprestapi.repository.ReviewRepository;
import com.springboot.movie.app.springbootmovieapprestapi.service.ReviewService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.support.MethodOverride;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private MovieRepository movieRepository;

    private ModelMapper modelMapper;
    @Override
    public ReviewDto createReview(long movieId, ReviewDto reviewDto) {

        Review review = mapToEntity(reviewDto);

        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new ResourceNotFoundException("Movie", "id", movieId));

        review.setMovie(movie);

        Review newReview = reviewRepository.save(review);

        return mapToDTO(newReview);
    }

    private ReviewDto mapToDTO(Review review) {
        ReviewDto reviewDto = modelMapper.map(review, ReviewDto.class);
        return  reviewDto;
    }

//        ReviewDto reviewDto = new ReviewDto();
//        reviewDto.setId(review.getId());
//        reviewDto.setName(review.getName());
//        reviewDto.setEmail(review.getEmail());
//        reviewDto.setBody(review.getBody());
//        reviewDto.setRating(review.getRating());
//
//        return reviewDto;
//    }

    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = modelMapper.map(reviewDto, Review.class);
        return review;
    }

//        Review review = new Review();
//        review.setId(reviewDto.getId());
//        review.setName(reviewDto.getName());
//        review.setEmail(reviewDto.getEmail());
//        review.setBody(reviewDto.getBody());
//        review.setRating(reviewDto.getRating());
//
//        return review;
//    }
}

