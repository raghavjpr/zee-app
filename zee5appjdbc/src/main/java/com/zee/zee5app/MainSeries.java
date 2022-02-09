package com.zee.zee5app;

import java.io.IOException;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.service.SeriesService;
import com.zee.zee5app.service.impl.SeriesServiceImpl;

public class MainSeries {

	public static void main(String[] args) {
		SeriesService seriesService = null;
		try {
			seriesService = SeriesServiceImpl.getInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Adding se00601: ");
		try {
			Series series = new Series("se00601", "Series1", 18, "Thriller", 6500, new java.util.Date(121, 3, 20),
					new String[] { "Cast 1", "Cast2" }, "Hindi", 35, null);
			System.out.println(seriesService.addSeries(series));
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Adding se03021: ");
		try {
			Series series = new Series("se03021", "Series1", 18, "Thriller", 6500, new java.util.Date(121, 3, 20),
					new String[] { "Cast 1", "Cast2" }, "Hindi", 35, null);
			System.out.println(seriesService.addSeries(series));
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Searching se00021: ");
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
	}

}