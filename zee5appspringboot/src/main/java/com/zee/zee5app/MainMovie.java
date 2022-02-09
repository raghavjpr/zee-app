package com.zee.zee5app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.service.MovieService;

@SpringBootApplication
public class MainMovie {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Zee5appspringbootApplication.class, args);

		MovieService movieService = applicationContext.getBean(MovieService.class);

		Movie movie = new Movie("mv0003", "DDLJ", 13, "SliceOfLife", 7200, new Date(400), "Cast 1, Cast 2", "Hindi", null);
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream("C:\\Users\\raghav.gupta\\Documents\\Tom&JerryFull.mp4");
			File file = new File("C:\\Users\\raghav.gupta\\Documents\\Tom&JerryFull.mp4");
			movie.setTrailer("C:\\Users\\raghav.gupta\\Documents\\MovieStore\\" + file.getName());
			Movie result = movieService.addMovie(movie);
			if (result != null) {
				fileOutputStream = new FileOutputStream("C:\\Users\\raghav.gupta\\Documents\\MovieStore\\" + file.getName());
				byte[] data = new byte[(int) file.length()];
				fileInputStream.read(data);
				fileOutputStream.write(data);
			}
			System.out.println(result);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
				fileOutputStream.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

//		Optional<Movie> optional = movieService.getMovieById("mv0003");
//		if(optional.isEmpty()) {
//			System.out.println("Record Not Found");
//		}else {
//			Movie movie = optional.get();
//			FileOutputStream fileOutputStream = null;
//			try {
//				fileOutputStream = new FileOutputStream("C:\\Users\\raghav.gupta\\Documents\\Read\\test.mp4");
//				fileOutputStream.write(movie.getTrailer());
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}finally {
//				try {
//					fileOutputStream.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		
//
//		
//		Optional<Movie> optional = movieService.getMovieById("mv0001");
//		if (optional.isEmpty()) {
//			System.out.println("No Movie Found");
//		} else {
//			System.out.println(movieService);
//		}
//
//		try {
//			movieService.deleteMovieById("mv0001");
//		} catch (IdNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		movieService.getAllMoviesList().forEach(e -> System.out.println(e));

		applicationContext.close();
	}

}
