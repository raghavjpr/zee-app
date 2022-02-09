package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository = null;

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
