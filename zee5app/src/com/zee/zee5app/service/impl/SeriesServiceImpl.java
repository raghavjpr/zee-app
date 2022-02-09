package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.LocationNotFoundException;
import com.zee.zee5app.exception.NameNotFoundException;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.repository.impl.SeriesRepositoryImpl;
import com.zee.zee5app.service.SeriesService;

public class SeriesServiceImpl implements SeriesService {
	
	private SeriesServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	private static SeriesService seriesService;
	public static SeriesService getInstance() {
		if(seriesService == null)
			seriesService = new SeriesServiceImpl();
		return seriesService;
	}
	
	SeriesRepository seriesRepository = SeriesRepositoryImpl.getInstance();
	@Override
	public String addSeries(Series series) {
		// TODO Auto-generated method stub
		return this.seriesRepository.addSeries(series);
	}

	@Override
	public String updateSeries(String id, Series series) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.seriesRepository.updateSeries(id, series);
	}

	@Override
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.seriesRepository.getSeriesById(id);
	}

	@Override
	public Series getSeriesByLocation(String location) throws LocationNotFoundException {
		// TODO Auto-generated method stub
		return this.seriesRepository.getSeriesByLocation(location);
	}

	@Override
	public Optional<Series> getSeriesByName(String series_name) throws NameNotFoundException {
		// TODO Auto-generated method stub
		return this.seriesRepository.getSeriesByName(series_name);
	}

	@Override
	public List<Series> getAllSeries() {
		// TODO Auto-generated method stub
		return this.seriesRepository.getAllSeries();
	}

	@Override
	public String deleteSeriesById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.seriesRepository.deleteSeriesById(id);
	}

}
