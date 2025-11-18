package org.example.HW.Easy.Repo;
import org.example.HW.Easy.Entity.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findAll();
    List<Movie> findByDirector(String director);
    void addMovie(Movie movie);
}
