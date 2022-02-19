package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Override
	public Movie addMovie(Movie movie) {
		Movie movie2 = movieRepository.save(movie);
		if (movie2 != null) {
			return movie;
		} else {
			return null;
		}
	}

	@Override
	public String deleteMovieById(String id) throws IdNotFoundException {
		Optional<Movie> optional = movieRepository.findById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("No Record Found");
		} else {
			movieRepository.deleteById(id);
			return "success";
		}
	}

	@Override
	public Optional<Movie> getMovieById(String id) {
		return movieRepository.findById(id);
	}

	@Override
	public List<Movie> getAllMoviesList() {
		return movieRepository.findAll();
	}

	@Override
	public Movie[] getAllMovie() {
		List<Movie> list = movieRepository.findAll();
		Movie[] array = new Movie[list.size()];
		return list.toArray(array);
	}

	@Override
	public List<Movie> getMovieByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
