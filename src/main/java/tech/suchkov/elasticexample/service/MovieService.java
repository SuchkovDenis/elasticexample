package tech.suchkov.elasticexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.suchkov.elasticexample.entity.Movie;
import tech.suchkov.elasticexample.entity.MovieDoc;
import tech.suchkov.elasticexample.repository.MovieElasticsearchRepository;
import tech.suchkov.elasticexample.repository.MovieRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Comparator.comparingInt;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieElasticsearchRepository movieElasticsearchRepository;

    public Page<Movie> searchMovies(String query, int page, int size) {
        var pageable = PageRequest.of(page, size);
        return movieRepository.searchByQuery(query, pageable);
    }

    public Page<Movie> searchMoviesViaElastic(String searchText, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MovieDoc> searchResults = movieElasticsearchRepository.searchByQuery(searchText, pageable);

        Map<Long, Integer> idsMap = new HashMap<>();
        List<MovieDoc> movieDocs = searchResults.getContent();
        for (int i = 0; i < movieDocs.size(); i++) {
            idsMap.put(movieDocs.get(i).getId(), i);
        }

        Set<Long> ids = idsMap.keySet();

        List<Movie> moviesFromDb = movieRepository.findAllById(ids);
        moviesFromDb.sort(comparingInt(movie -> idsMap.get(movie.getId())));

        return new PageImpl<>(moviesFromDb, pageable, searchResults.getTotalElements());
    }
}