package com.springboot.movie.app.springbootmovieapprestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        description = "ReviewDto Model Information"
)
public class ReviewDto {

    private Long id;

    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @NotEmpty(message = "Email should not be null or empty")
    private String email;

    @NotEmpty(message = "Body should not be null or empty")
    @Size(min = 12, message = " Review's body must be minimum 12 characters ")
    private String body;

    @NotNull(message = "Rating should not be null or empty")
    private int rating;

}
