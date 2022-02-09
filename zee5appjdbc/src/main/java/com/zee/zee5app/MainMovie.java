package com.zee.zee5app;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.impl.MovieServiceImpl;

public class MainMovie {

	public static void main(String[] args) {

		MovieService movieService = null;
		try {
			movieService = MovieServiceImpl.getInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Adding mv00043: ");
		try {
			Movie movie = new Movie("mv00043", "Test1", 13, "Horror", 5000, new Date(121, 0, 1),
					new String[] { "Tester1", "Tester2" }, "English", "https://www.youtube.com/watch?v=NUm4Ht8mZ1s");
			System.out.println(movieService.addMovie(movie));
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Adding mv00402: ");
		try {
			Movie movie = new Movie("mv00402", "Test2", 13, "Horror", 5000, new Date(121, 0, 1),
					new String[] { "Tester1", "Tester2" }, "English", "https://www.youtube.com/watch?v=NUm4Ht8mZ1s");
			System.out.println(movieService.addMovie(movie));
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Searching mv0001: ");
		try {
			Optional<Movie> result = movieService.getMovieById("mv0001");
			if (result.isPresent()) {
				System.out.println(result.get());
			} else {
				System.out.println("fail");
			}
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Searching mv00001: ");
		try {
			Optional<Movie> result = movieService.getMovieById("mv00001");
			if (result.isPresent()) {
				System.out.println(result.get());
			} else {
				System.out.println("fail");
			}
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}