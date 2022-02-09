package com.zee.zee5app;

import java.sql.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.service.EpisodeService;
import com.zee.zee5app.service.SeriesService;

@SpringBootApplication
public class MainSeries {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Zee5appspringbootApplication.class, args);

		SeriesService seriesService = applicationContext.getBean(SeriesService.class);

		Series series = new Series("se0003", "TOT", 15, "Historical", new Date(400), "Cast 1, Cast 2", "English", 25, "www.youtube.com", null);
		String result = seriesService.addSeries(series);
		System.out.println(result);
		
		EpisodeService episodeService = applicationContext.getBean(EpisodeService.class);
		Episode episode = new Episode("ep0001", "Hammer", 3600, "www.yt.com", "www.ty.com", series);
		episodeService.addEpisode(episode);
		applicationContext.close();
	}

}
