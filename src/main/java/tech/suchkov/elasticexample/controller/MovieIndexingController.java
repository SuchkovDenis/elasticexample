package tech.suchkov.elasticexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.suchkov.elasticexample.service.MovieIndexingService;

@RestController
public class MovieIndexingController {

    @Autowired
    private MovieIndexingService movieIndexingService;

    @GetMapping("/reindex")
    public String reindexMovies() {
        movieIndexingService.reindexAllMovies();
        return "Переиндексация завершена!";
    }
}
