package com.springboot.movie.app.springbootmovieapprestapi.payload;

import com.springboot.movie.app.springbootmovieapprestapi.dto.MovieDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "MoviePaginationResponse Model Information"
)
public class MoviePaginationResponse {
    private List<MovieDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
