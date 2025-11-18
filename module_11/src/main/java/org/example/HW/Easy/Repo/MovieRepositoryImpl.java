package org.example.HW.Easy.Repo;

import org.example.HW.Easy.Entity.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private final List<Movie> movies = new ArrayList<>();

    public MovieRepositoryImpl() {
        movies.add(new Movie("Inception", "Christopher Nolan", 2010));
        movies.add(new Movie("The Matrix", "Wachowski Sisters", 1999));
        movies.add(new Movie("Pulp Fiction", "Quentin Tarantino", 1994));
    }

    @Override
    public List<Movie> findAll() {
        return movies;
    }

    @Override
    public List<Movie> findByDirector(String director) {
        return movies.stream()
                .filter(m -> m.getDirector().equalsIgnoreCase(director))
                .toList();
    }

    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
}