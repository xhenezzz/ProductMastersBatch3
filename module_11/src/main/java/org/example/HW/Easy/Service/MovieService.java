package org.example.HW.Easy.Service;
import org.example.HW.Easy.Entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    List<Movie> getMoviesByDirector(String director);
    void addMovie(Movie movie);
}
