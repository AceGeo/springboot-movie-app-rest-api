package com.springboot.movie.app.springbootmovieapprestapi.repository;

import com.springboot.movie.app.springbootmovieapprestapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
