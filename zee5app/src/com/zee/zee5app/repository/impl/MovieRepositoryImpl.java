package com.zee.zee5app.repository.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.LocationNotFoundException;
import com.zee.zee5app.exception.NameNotFoundException;
import com.zee.zee5app.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
	
	private MovieRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	private static MovieRepository movieRepository;
	public static MovieRepository getInstance() {
		if(movieRepository == null)
			movieRepository = new MovieRepositoryImpl();
		return movieRepository;
	}
	
	private Set<Movie> setMovie = new HashSet<Movie>();
	
	@Override
	public String addMovie(Movie movie) {
		// TODO Auto-generated method stub
		boolean result = this.setMovie.add(movie);
		if (result) {
			return "SUCCESS ==> Movie successfully added in  HashSet.";
		}
		return "FAILED ==> Movie addition was not successfull.";
	}
	@Override
	public String updateMovie(String id, Movie movie) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Movie> optionalMovie = this.geMovieById(id);
		if (optionalMovie.isPresent()) {
			this.deleteMovieById(id);
			this.addMovie(movie);
			return "SUCCESS ==> Movie updated";
		}
		return "FAILED ==> No such element present in HashSet.";
	}
	// Java does not have a direct method to update an object in a HashSet. You can do by removing that object and adding the replacement one.
	@Override
	public Optional<Movie> geMovieById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Movie movie = null;
		for (Movie movieTemp : setMovie) {
			if (movieTemp.getId().equals(id)) {
				movie = movieTemp;
				break;				
			}
		}		
		return Optional.of(Optional.ofNullable(movie).orElseThrow(()-> new IdNotFoundException("FAILED ==> No movie with such ID.")));
	}
	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return new ArrayList<Movie>(setMovie);
	}
	@Override
	public String deleteMovieById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Movie> optionalMovie = this.geMovieById(id);
		if(optionalMovie.isPresent()) {
			setMovie.remove(optionalMovie.get());
			return "SUCCESS ==> " + optionalMovie.get().getMovie_name() + " deleted.";
		}
		return "FAILED ==> No such movie present by ID " + id;
	}
	@Override
	public Movie geMovieByLocation(String location) throws LocationNotFoundException {
		// TODO Auto-generated method stub
		Movie movie = null;
		for (Movie movieTemp : setMovie) {
			if (movieTemp.getLocation().equals(location)) {
				movie = movieTemp;
				break;				
			}
		}
		if (movie == null) {
			throw new LocationNotFoundException("FAILED ==> No movie with such Location.");
		} else {
			return movie;
		}
	}
	@Override
	public Optional<Movie> geMovieByName(String movie_name) throws NameNotFoundException {
		// TODO Auto-generated method stub
		Movie movie = null;
		for (Movie movieTemp : setMovie) {
			if (movieTemp.getMovie_name().equals(movie_name)) {
				movie = movieTemp;
				break;				
			}
		}		
		return Optional.of(Optional.ofNullable(movie).orElseThrow(()-> new NameNotFoundException("FAILED ==> No movie with such Name.")));
	}

}
