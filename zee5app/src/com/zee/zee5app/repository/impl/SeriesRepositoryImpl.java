package com.zee.zee5app.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.LocationNotFoundException;
import com.zee.zee5app.exception.NameNotFoundException;
import com.zee.zee5app.repository.SeriesRepository;

public class SeriesRepositoryImpl implements SeriesRepository {
	
	private SeriesRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	private static SeriesRepository seriesRepository;
	public static SeriesRepository getInstance() {
		if(seriesRepository == null)
			seriesRepository = new SeriesRepositoryImpl();
		return seriesRepository;
	}
	
	private TreeSet<Series> seriesSet = new TreeSet<Series>();
	
	@Override
	public String addSeries(Series series) {
		// TODO Auto-generated method stub
		boolean result = this.seriesSet.add(series);
		if (result) {
			return "SUCCESS ==> Series successfully added in  TreeSet.";
		}
		return "FAILED ==> Series addition was not successfull.";
	}
	@Override
	public String updateSeries(String id, Series series) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Series> optionalMovie = this.getSeriesById(id);
		if (optionalMovie.isPresent()) {
			this.deleteSeriesById(id);
			this.addSeries(series);
			return "SUCCESS ==> Movie updated";
		}
		return "FAILED ==> No such element present in HashSet.";
	}
	@Override
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Series series = null;
		for (Series seriesTemp : seriesSet) {
			if (seriesTemp.getId().equals(id)) {
				series = seriesTemp;
				break;				
			}
		}		
		return Optional.of(Optional.ofNullable(series).orElseThrow(()-> new IdNotFoundException("FAILED ==> No series with such ID.")));
	}
	@Override
	public Series getSeriesByLocation(String location) throws LocationNotFoundException {
		// TODO Auto-generated method stub
		Series series = null;
		for (Series seriesTemp : seriesSet) {
			if (seriesTemp.getLocation().equals(location)) {
				series = seriesTemp;
				break;				
			}
		}
		if (series == null) {
			throw new LocationNotFoundException("FAILED ==> No series with such Location.");
		} else {
			return series;
		}
	}
	@Override
	public Optional<Series> getSeriesByName(String series_name) throws NameNotFoundException {
		// TODO Auto-generated method stub
		Series series = null;
		for (Series seriesTemp : seriesSet) {
			if (seriesTemp.getSeries_name().equals(series_name)) {
				series = seriesTemp;
				break;				
			}
		}			
		return Optional.of(Optional.ofNullable(series).orElseThrow(()-> new NameNotFoundException("FAILED ==> No series with such Name.")));
	}
	@Override
	public List<Series> getAllSeries() {
		// TODO Auto-generated method stub
		return new ArrayList<Series>(seriesSet);
	}
	@Override
	public String deleteSeriesById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Series> optionalMovie = this.getSeriesById(id);
		if(optionalMovie.isPresent()) {
			seriesSet.remove(optionalMovie.get());
			return "SUCCESS ==> " + optionalMovie.get().getSeries_name() + " deleted.";
		}
		return "FAILED ==> No such movie present by ID " + id;
	}

}
