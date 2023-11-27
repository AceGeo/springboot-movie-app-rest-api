package com.springboot.movie.app.springbootmovieapprestapi.repository;

import com.springboot.movie.app.springbootmovieapprestapi.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByMovieId(long movieId);
}
