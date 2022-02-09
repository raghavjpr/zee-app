package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.LocationNotFoundException;
import com.zee.zee5app.exception.NameNotFoundException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.repository.impl.MovieRepositoryImpl;
import com.zee.zee5app.service.MovieService;

public class MovieServiceImpl implements MovieService {
	
	private MovieServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	private static MovieService movieService;
	public static MovieService getInstance() {
		if(movieService == null)
			movieService = new MovieServiceImpl();
		return movieService;
	}
	
	MovieRepository movieRepository = MovieRepositoryImpl.getInstance();
	@Override
	public String addMovie(Movie movie) {
		// TODO Auto-generated method stub
		return this.movieRepository.addMovie(movie);
	}

	@Override
	public String updateMovie(String id, Movie movie) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.movieRepository.updateMovie(id, movie);
	}

	@Override
	public Optional<Movie> geMovieById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.movieRepository.geMovieById(id);
	}

	@Override
	public Movie geMovieByLocation(String location) throws LocationNotFoundException {
		// TODO Auto-generated method stub
		return this.movieRepository.geMovieByLocation(location);
	}

	@Override
	public Optional<Movie> geMovieByName(String movie_name) throws NameNotFoundException {
		// TODO Auto-generated method stub
		return this.movieRepository.geMovieByName(movie_name);
	}

	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return this.movieRepository.getAllMovies();
	}

	@Override
	public String deleteMovieById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.movieRepository.deleteMovieById(id);
	}
	

}
