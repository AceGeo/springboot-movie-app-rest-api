package com.springboot.movie.app.springbootmovieapprestapi.controller;

import com.springboot.movie.app.springbootmovieapprestapi.dto.MovieDto;

import com.springboot.movie.app.springbootmovieapprestapi.payload.MoviePaginationResponse;
import com.springboot.movie.app.springbootmovieapprestapi.service.MovieService;
import com.springboot.movie.app.springbootmovieapprestapi.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/movies")
@Tag(
        name= "CRUD REST APIs for Movie Resource"
)
public class MovieController {

    private MovieService movieService;

    @Operation(
            summary = "Create Movie REST API",
            description = "Creation of Movie REST API in order to save movies into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto) {
        return new ResponseEntity<>(movieService.createMovie(movieDto), HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get All Movies REST API",
            description = "Get All the Movies REST API in order to get all movies from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping()
    public MoviePaginationResponse getAllMovies(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY, required  = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return movieService.getAllMovies(pageNo, pageSize, sortBy,sortDir);
    }
    @Operation(
            summary = "Get Movie By Id REST API",
            description = "Get Movie By Id REST API in order to get movie by Id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable(name="id") long id) {
        return new ResponseEntity<MovieDto>(movieService.getMovieById(id),HttpStatus.OK);
    }
    @Operation(
            summary = "Update Movie REST API",
            description = "Update Movie REST API in order to update movie into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@Valid @RequestBody MovieDto postDto, @PathVariable(name="id") long id) {
        return new ResponseEntity<>(movieService.updateMovie(postDto,id),HttpStatus.CREATED);
    }

    @Operation(
            summary = "Delete Movie REST API",
            description = "Delete Movie REST API in order to delete movie from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable(name="id") long id) {

        movieService.deleteMovieById(id);
        return new ResponseEntity<>("The movie has been succesfully deleted.", HttpStatus.OK);
    }

    @Operation(
            summary = "Get Movie By Category REST API",
            description = "Get Movie by category REST API in order to get movie by its category from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/category/{id}")
    public ResponseEntity<List<MovieDto>> getPostsByCategory(Long categoryId){
        List<MovieDto> movieDtos = movieService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(movieDtos);
    }
}
