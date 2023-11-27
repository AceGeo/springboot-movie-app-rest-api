package com.springboot.movie.app.springbootmovieapprestapi.service;

import com.springboot.movie.app.springbootmovieapprestapi.dto.MovieDto;
import com.springboot.movie.app.springbootmovieapprestapi.entity.Movie;
import com.springboot.movie.app.springbootmovieapprestapi.payload.MoviePaginationResponse;

import java.util.List;

public interface MovieService {

    MovieDto createMovie(MovieDto movieDto);

    MoviePaginationResponse getAllMovies(int pageNo, int pageSize, String sortBy, String sortDir);

    MovieDto getMovieById(long id);

    MovieDto updateMovie(MovieDto movieDto, long id);

    void deleteMovieById(long id);

    List<MovieDto> getPostsByCategory(Long categoryId);
}

