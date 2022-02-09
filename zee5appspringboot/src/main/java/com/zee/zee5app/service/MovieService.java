package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;

public interface MovieService {
	public Movie addMovie(Movie movie);

	public String deleteMovieById(String id) throws IdNotFoundException;

	public Optional<Movie> getMovieById(String id);

	public List<Movie> getAllMoviesList();

	public Movie[] getAllMovie();

	public List<Movie> getMovieByName(String name);
}
