package org.example.HW.Easy.Service;
import org.example.HW.Easy.Entity.Movie;
import org.example.HW.Easy.Repo.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesByDirector(String director) {
        return movieRepository.findByDirector(director);
    }

    @Override
    public void addMovie(Movie movie) {

        if (movie.getYear() < 1900) {
            throw new IllegalArgumentException("Year must be >= 1900");
        }
        if (movie.getTitle() == null || movie.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (movie.getDirector() == null || movie.getDirector().isBlank()) {
            throw new IllegalArgumentException("Director cannot be empty");
        }
        movieRepository.addMovie(movie);
    }


}
