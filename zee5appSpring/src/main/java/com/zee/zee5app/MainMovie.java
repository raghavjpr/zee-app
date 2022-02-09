package com.zee.zee5app;

import java.sql.Date;
import java.util.Optional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.service.MovieService;

public class MainMovie {

	public static void main(String[] args) {

		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		MovieService movieService = applicationContext.getBean(MovieService.class);

		System.out.println("Adding mv04043: ");
		try {
			Movie movie = new Movie("mv04043", "Test1", 13, "Horror", 5000, new Date(121, 0, 1),
					new String[] { "Tester1", "Tester2" }, "English", "https://www.youtube.com/watch?v=NUm4Ht8mZ1s");
			System.out.println(movieService.addMovie(movie));
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println("Adding mv00402: ");
//		try {
//			Movie movie = new Movie("mv00402", "Test2", 13, "Horror", 5000, new Date(121, 0, 1),
//					new String[] { "Tester1", "Tester2" }, "English", "https://www.youtube.com/watch?v=NUm4Ht8mZ1s");
//			System.out.println(movieService.addMovie(movie));
//		} catch (InvalidIdLengthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

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

		applicationContext.close();

	}
}