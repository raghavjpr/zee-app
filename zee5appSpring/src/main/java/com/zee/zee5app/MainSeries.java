package com.zee.zee5app;

import java.util.Optional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.service.SeriesService;

public class MainSeries {

	public static void main(String[] args) {

		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		SeriesService seriesService = applicationContext.getBean(SeriesService.class);

		System.out.println("Adding se00001: ");
		try {
			Series series = new Series("se00001", "Series1", 18, "Thriller", 6500, new java.util.Date(121, 3, 20),
					new String[] { "Cast 1", "Cast2" }, "Hindi", 35, null);
			System.out.println(seriesService.addSeries(series));
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Adding se00021: ");
		try {
			Series series = new Series("se00021", "Series1", 18, "Thriller", 6500, new java.util.Date(121, 3, 20),
					new String[] { "Cast 1", "Cast2" }, "Hindi", 35, null);
			System.out.println(seriesService.addSeries(series));
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Searching mv0001: ");
		try {
			Optional<Series> result = seriesService.getSeriesById("se00021");
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