package com.springboot.movie.app.springbootmovieapprestapi.service.serviceImpl;

import com.springboot.movie.app.springbootmovieapprestapi.dto.MovieDto;
import com.springboot.movie.app.springbootmovieapprestapi.entity.Category;
import com.springboot.movie.app.springbootmovieapprestapi.entity.Movie;
import com.springboot.movie.app.springbootmovieapprestapi.exception.ResourceNotFoundException;
import com.springboot.movie.app.springbootmovieapprestapi.payload.MoviePaginationResponse;
import com.springboot.movie.app.springbootmovieapprestapi.repository.CategoryRepository;
import com.springboot.movie.app.springbootmovieapprestapi.repository.MovieRepository;
import com.springboot.movie.app.springbootmovieapprestapi.service.MovieService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private ModelMapper modelMapper;

    private CategoryRepository categoryRepository;
    @Override
    public MovieDto createMovie(MovieDto movieDto) {

        //search if the category exists
        Category category= categoryRepository.findById(movieDto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category","id", movieDto.getCategoryId()));

        // Create Dto to Entity
        Movie movie = mapTOEntity(movieDto);
        Movie newMovie = movieRepository.save(movie);

        // Create Entity to Dto
        MovieDto movieResponse = mapToDTO(newMovie);
        return movieResponse;
    }

    @Override
    public MoviePaginationResponse getAllMovies(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Movie> movies = movieRepository.findAll(pageable);

        // get content for page object
        List<Movie> moviesList = movies.getContent();
        List<MovieDto> content = moviesList.stream()
                .map(movie -> mapToDTO(movie)).collect(Collectors.toList());

        MoviePaginationResponse moviePaginationResponse = new MoviePaginationResponse();
        moviePaginationResponse.setContent(content);
        moviePaginationResponse.setPageNo(movies.getNumber());
        moviePaginationResponse.setPageSize(movies.getSize());
        moviePaginationResponse.setTotalElements(movies.getTotalElements());
        moviePaginationResponse.setPageSize(movies.getTotalPages());
        moviePaginationResponse.setLast(movies.isLast());

        return moviePaginationResponse;
    }

    @Override
    public MovieDto getMovieById(long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(
                        ()-> new ResourceNotFoundException("Movie","id", id));
        return mapToDTO(movie);
    }

    @Override
    public MovieDto updateMovie(MovieDto movieDto, long id) {

        //search if the category exists
        Category category= categoryRepository.findById(movieDto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category","id", movieDto.getCategoryId()));

        //Get Movie by the id from the database
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie","id", id));

        movie.setTitle(movieDto.getTitle());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setDescription(movieDto.getDescription());
        movie.setGenre(movieDto.getGenre());
        movie.setCountry(movieDto.getCountry());
        movie.setCategory(category);

        Movie updateMovie = movieRepository.save(movie);
        return mapToDTO(updateMovie);
    }

    @Override
    public void deleteMovieById(long id) {
        //Get Movie by the id from the database
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie","id", id));
        movieRepository.delete(movie);
    }

    @Override
    public List<MovieDto> getPostsByCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        List<Movie> movies  = movieRepository.findByCategoryId(categoryId);

        return movies.stream().map((movie) -> mapToDTO(movie))
                .collect(Collectors.toList());
    }


    // convert Entity to Dto
    private MovieDto mapToDTO(Movie movie) {
        MovieDto movieDto = modelMapper.map(movie,MovieDto.class);
        return movieDto;
    }
//        MovieDto movieDto = new MovieDto();
//        movieDto.setId(movie.getId());
//        movieDto.setTitle(movie.getTitle());
//        movieDto.setReleaseDate(movie.getReleaseDate());
//        movieDto.setGenre(movie.getGenre());
//        movieDto.setDescription(movie.getDescription());
//        movieDto.setCountry(movie.getCountry());
//        return movieDto;
//    }

        // convert Dto to Entity
        private Movie mapTOEntity (MovieDto movieDto){
        Movie movie = modelMapper.map(movieDto, Movie.class);
        return movie;
    }
//            Movie movie =new Movie();
//            movie.setId(movieDto.getId());
//            movie.setTitle(movieDto.getTitle());
//            movie.setReleaseDate(movieDto.getReleaseDate());
//            movie.setGenre(movieDto.getGenre());
//            movie.setDescription(movieDto.getDescription());
//            movie.setCountry(movieDto.getCountry());
//            return movie;
//        }
    }