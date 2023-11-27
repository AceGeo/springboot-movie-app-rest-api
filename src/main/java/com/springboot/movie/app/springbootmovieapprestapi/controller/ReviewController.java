package com.springboot.movie.app.springbootmovieapprestapi.controller;

import com.springboot.movie.app.springbootmovieapprestapi.dto.ReviewDto;
import com.springboot.movie.app.springbootmovieapprestapi.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Tag(
        name= "Post reviews for the Movies by Id REST APIs"
)
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Operation(
            summary = "Post reviews for the Movies by Id REST APIs",
            description = "Creation of Review for Movies REST API in order to save the reviews of Movie by Id into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping("/movies/{id}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "id") long id,
                                                  @Valid
                                                  @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(id,reviewDto), HttpStatus.CREATED);
    }
}
