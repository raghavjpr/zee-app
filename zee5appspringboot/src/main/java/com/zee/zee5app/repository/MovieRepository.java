package com.zee.zee5app.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

	Boolean existsByMovieName(String movieName);

	Optional<Movie> findByMovieNameAndLanguage(String movieName, String language);

	Optional<Movie> findByMovieName(String movieName);

	Optional<Movie> findByMovieNameAndReleaseDate(String movieName, Date releaseDate);
	
	// find the list based on cast name
	Optional<Movie> findByCast(String cast);

}
