package tech.suchkov.elasticexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.suchkov.elasticexample.entity.Movie;
import tech.suchkov.elasticexample.entity.MovieDoc;
import tech.suchkov.elasticexample.repository.MovieElasticsearchRepository;
import tech.suchkov.elasticexample.repository.MovieRepository;

import java.util.List;

@Service
public class MovieIndexingService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieElasticsearchRepository movieElasticsearchRepository;

    @Transactional(readOnly = true)
    public void reindexAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        movieElasticsearchRepository.saveAll(
                movies.stream().map(
                        movie -> new MovieDoc(
                                movie.getId(),
                                movie.getMovie(),
                                movie.getOverview()
                        )
                ).toList()
        );
    }
}
