package com.springboot.movie.app.springbootmovieapprestapi.repository;

import com.springboot.movie.app.springbootmovieapprestapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> findByCategoryId(Long categoryId);
}
