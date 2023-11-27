package com.springboot.movie.app.springbootmovieapprestapi.service;

import com.springboot.movie.app.springbootmovieapprestapi.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto getCategory(Long categoryId);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(long categoryId, CategoryDto categoryDto);

    void deleteComment(Long categoryId);

}