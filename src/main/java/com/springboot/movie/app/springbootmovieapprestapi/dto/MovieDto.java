package com.springboot.movie.app.springbootmovieapprestapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Schema(
        description = "MovieDto Model Information"
)
public class MovieDto {

    private Long id;
    @NotEmpty
    @Size(min = 2, message = "Movie title should have at least 2 characters")
    private String title;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;
    @NotEmpty
    private String genre;
    @NotEmpty
    @Size(min= 10, message = "Movie description should have at least 10 characters")
    private String description;
    @NotEmpty
    private String country;

    private Set<ReviewDto> reviews;
    private Long categoryId;

}
