package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.LocationNotFoundException;
import com.zee.zee5app.exception.NameNotFoundException;

public interface MovieRepository {
	
	public String addMovie(Movie movie);
	public String updateMovie(String id, Movie movie) throws IdNotFoundException;
	public Optional<Movie> geMovieById(String id) throws IdNotFoundException;
	public Movie geMovieByLocation(String location) throws LocationNotFoundException;
	public Optional<Movie> geMovieByName(String movie_name) throws NameNotFoundException;
	public List<Movie> getAllMovies();
	public String deleteMovieById(String id) throws IdNotFoundException;
	
	
	
}
