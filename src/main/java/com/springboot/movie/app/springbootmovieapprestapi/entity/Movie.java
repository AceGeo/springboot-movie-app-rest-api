package com.springboot.movie.app.springbootmovieapprestapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name="movies", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Movie {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(name="title", nullable=false)
    private String title;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name="release_date", nullable = false)
    private LocalDate releaseDate;
    @Column(name="genre", nullable=false)
    private String genre;
    @Column(name="description", nullable=false)
    private String description;
    @Column(name="country", nullable=false)
    private String country;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}

