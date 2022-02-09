package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.repository.impl.SeriesRepositoryImpl;
import com.zee.zee5app.service.SeriesService;

public class SeriesServiceImpl implements SeriesService {
	SeriesRepository seriesRepository = null;

	private SeriesServiceImpl() throws IOException {
		seriesRepository = SeriesRepositoryImpl.getInstance();
	}

	private static SeriesService seriesService;

	public static SeriesService getInstance() throws IOException {
		if (seriesService == null)
			seriesService = new SeriesServiceImpl();
		return seriesService;
	}

	@Override
	public String addSeries(Series series) {
		// TODO Auto-generated method stub
		return this.seriesRepository.addSeries(series);
	}

	@Override
	public String updateSeriesById(String id, Series series) {
		// TODO Auto-generated method stub
		return this.seriesRepository.updateSeriesById(id, series);
	}

	@Override
	public String deleteSeriesById(String id) {
		// TODO Auto-generated method stub
		return this.seriesRepository.deleteSeriesById(id);
	}

	@Override
	public Optional<Series> getSeriesById(String id) throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		return this.seriesRepository.getSeriesById(id);
	}

	@Override
	public List<Series> getAllSeriesList() throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		return this.seriesRepository.getAllSeriesList();
	}

	@Override
	public Series[] getAllSeries() throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		return this.seriesRepository.getAllSeries();
	}

}
