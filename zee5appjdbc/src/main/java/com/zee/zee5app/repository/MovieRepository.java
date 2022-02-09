package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.InvalidIdLengthException;

public interface MovieRepository {
	public String addMovie(Movie movie);

	public String updateMovieById(String id, Movie movie);

	public String deleteMovieById(String id);

	public Optional<Movie> getMovieById(String id) throws InvalidIdLengthException;

	public List<Movie> getAllMoviesList() throws InvalidIdLengthException;

	public Movie[] getAllMovie() throws InvalidIdLengthException;

	public List<Movie> getMovieByName(String name) throws InvalidIdLengthException;

}
