package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.LocationNotFoundException;
import com.zee.zee5app.exception.NameNotFoundException;

public interface SeriesRepository {
	
	public String addSeries(Series series);
	public String updateSeries(String id, Series series) throws IdNotFoundException;
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException;
	public Series getSeriesByLocation(String location) throws LocationNotFoundException;
	public Optional<Series> getSeriesByName(String series_name) throws NameNotFoundException;
	public List<Series> getAllSeries();
	public String deleteSeriesById(String id) throws IdNotFoundException;
	
	//Tree Set

}
