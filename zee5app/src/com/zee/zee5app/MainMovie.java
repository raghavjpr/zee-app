package com.zee.zee5app;

import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.LocationNotFoundException;
import com.zee.zee5app.exception.NameNotFoundException;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.impl.MovieServiceImpl;

public class MainMovie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Movie movie1 = null, movie2 = null, movie3 = null, movie4 = null, movie5 = null, movie6 = null, movie7 = null;
		
		try {
			movie1 = new Movie("00001", "TestMovie1", "TestMovieLocation1", "TestMovieDate1", "TestMovieTrailer1", "TestMovieLanguage1", null, "TestMovieLength1");
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			try {
				movie1 = new Movie("000001", "TestMovie1", "TestMovieLocation1", "TestMovieDate1", "TestMovieTrailer1", "TestMovieLanguage1", null, "TestMovieLength1");
			} catch (InvalidIdLengthException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			movie2 = new Movie("000002", "TestMovie2", "TestMovieLocation2", "TestMovieDate2", "TestMovieTrailer2", "TestMovieLanguage2", null, "TestMovieLength2");
			movie3 = new Movie("000003", "TestMovie3", "TestMovieLocation3", "TestMovieDate3", "TestMovieTrailer3", "TestMovieLanguage3", null, "TestMovieLength3");
			movie4 = new Movie("000004", "TestMovie4", "TestMovieLocation4", "TestMovieDate4", "TestMovieTrailer4", "TestMovieLanguage4", null, "TestMovieLength4");
			movie5 = new Movie("000005", "TestMovie5", "TestMovieLocation5", "TestMovieDate5", "TestMovieTrailer5", "TestMovieLanguage5", null, "TestMovieLength5");
			movie6 = new Movie("000006", "TestMovie6", "TestMovieLocation6", "TestMovieDate6", "TestMovieTrailer6", "TestMovieLanguage6", null, "TestMovieLength6");
			movie7 = new Movie("000007", "TestMovie7", "TestMovieLocation7", "TestMovieDate7", "TestMovieTrailer7", "TestMovieLanguage7", null, "TestMovieLength7");
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(movie1);
		System.out.println(movie2);
		System.out.println(movie3);
		System.out.println(movie4);
		System.out.println(movie5);
		System.out.println(movie6);
		
		MovieService movieService = MovieServiceImpl.getInstance();
		
		System.out.println("		***Adding movie1, movie2, movie3, movie4, movie5 & movie6.***");
		System.out.println(movieService.addMovie(movie1));
		System.out.println(movieService.addMovie(movie2));
		System.out.println(movieService.addMovie(movie3));
		System.out.println(movieService.addMovie(movie4));
		System.out.println(movieService.addMovie(movie5));
		System.out.println(movieService.addMovie(movie6));
		
		System.out.println("		***As it is a HashSet it will be random insetion.***");
		movieService.getAllMovies().forEach(e->System.out.println(e));
		
		System.out.println("		***Trying to add movie1 & movie6 once more.***");
		System.out.println(movieService.addMovie(movie1));
		System.out.println(movieService.addMovie(movie6));
		
		System.out.println("		***Updating movie6 with movie7.***");
		try {
			System.out.println(movieService.updateMovie("000006", movie7));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("		***Updating movie6 with movie7 again.***");
		try {
			System.out.println(movieService.updateMovie("000006", movie7));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		movieService.getAllMovies().forEach(e->System.out.println(e));
		
		System.out.println("		***Deleting movie7 which is present.***");
		try {
			System.out.println(movieService.deleteMovieById("000007"));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("		***Deleting movie6 which is not present.***");
		try {
			System.out.println(movieService.deleteMovieById("000006"));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		movieService.getAllMovies().forEach(e->System.out.println(e));
		
		System.out.println("		***Getting movie with id 000004(Present).***");
		Optional<Movie> optionalMovie = null;
		try {
			optionalMovie = movieService.geMovieById("000004");
			System.out.println(optionalMovie.get());
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("		***Getting movie with id 000006(Not Present).***");
		try {
			optionalMovie = movieService.geMovieById("000006");
			System.out.println(optionalMovie.get());
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("		***Getting movie with Location TestMovieLocation3(Present).***");
		Movie movie = null;
		try {
			movie = movieService.geMovieByLocation("TestMovieLocation3");
			System.out.println(movie);
		} catch (LocationNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("		***Getting movie with Location TestMovieLocation7(Not Present).***");
		try {
			movie = movieService.geMovieByLocation("TestMovieLocation7");
			System.out.println(movie);
		} catch (LocationNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("		***Getting movie with Name TestMovie5(Present).***");
		try {
			optionalMovie = movieService.geMovieByName("TestMovie5");
			System.out.println(optionalMovie.get());
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("		***Getting movie with Name TestMovie6(Not Present).***");
		try {
			optionalMovie = movieService.geMovieByName("TestMovie6");
			System.out.println(optionalMovie.get());
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		movieService.getAllMovies().forEach(e->System.out.println(e));
	}

}