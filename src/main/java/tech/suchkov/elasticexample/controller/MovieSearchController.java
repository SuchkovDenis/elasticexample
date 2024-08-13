package tech.suchkov.elasticexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.suchkov.elasticexample.entity.Movie;
import tech.suchkov.elasticexample.service.MovieService;

@Controller
public class MovieSearchController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String home() {
        return "search";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("query") String query,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model model
    ) {
        Page<Movie> moviesPage = movieService.searchMovies(query, page, size);
        model.addAttribute("moviesPage", moviesPage);
        model.addAttribute("query", query);
        return "search";
    }
}