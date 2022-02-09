package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.repository.impl.MovieRepositoryImpl;
import com.zee.zee5app.service.MovieService;

public class MovieServiceImpl implements MovieService {

	MovieRepository movieRepository = null;

	private MovieServiceImpl() throws IOException {
		// TODO Auto-generated constructor stub
		movieRepository = MovieRepositoryImpl.getInstance();
	}

	private static MovieService movieService;

	public static MovieService getInstance() throws IOException {
		if (movieService == null)
			movieService = new MovieServiceImpl();
		return movieService;
	}

	@Override
	public String addMovie(Movie movie) {
		// TODO Auto-generated method stub
		return this.movieRepository.addMovie(movie);
	}

	@Override
	public String updateMovieById(String id, Movie movie) {
		// TODO Auto-generated method stub
		return this.movieRepository.updateMovieById(id, movie);
	}

	@Override
	public String deleteMovieById(String id) {
		// TODO Auto-generated method stub
		return this.movieRepository.deleteMovieById(id);
	}

	@Override
	public Optional<Movie> getMovieById(String id) throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		return this.movieRepository.getMovieById(id);
	}

	@Override
	public List<Movie> getAllMoviesList() throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		return this.movieRepository.getAllMoviesList();
	}

	@Override
	public Movie[] getAllMovie() throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		return this.movieRepository.getAllMovie();
	}

	@Override
	public List<Movie> getMovieByName(String name) throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		return this.movieRepository.getMovieByName(name);
	}

}
