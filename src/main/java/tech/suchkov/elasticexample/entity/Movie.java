package tech.suchkov.elasticexample.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    private String movie;

    private Integer year;

    private String country;

    @Column(name = "rating_ball")
    private Float ratingBall;

    @Column(columnDefinition = "TEXT")
    private String overview;

    private String director;

    @Column(name = "screenwriter")
    private List<String> screenwriter;

    @Column(name = "actors")
    private List<String> actors;

    @Column(name = "url_logo")
    private String urlLogo;
}
