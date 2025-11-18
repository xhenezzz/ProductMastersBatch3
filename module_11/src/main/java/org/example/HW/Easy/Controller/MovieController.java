package org.example.HW.Easy.Controller;
import org.example.HW.Easy.Entity.Movie;
import org.example.HW.Easy.Service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/by-director")
    public List<Movie> getByDirector(@RequestParam String name) {
        return movieService.getMoviesByDirector(name);
    }

    @PostMapping("/add")
    public String addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return "Movie added!";
    }


}
