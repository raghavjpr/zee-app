package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.InvalidIdLengthException;

public interface SeriesRepository {
	public String addSeries(Series series);

	public String updateSeriesById(String id, Series series);

	public String deleteSeriesById(String id);

	public Optional<Series> getSeriesById(String id) throws InvalidIdLengthException;

	public List<Series> getAllSeriesList() throws InvalidIdLengthException;

	public Series[] getAllSeries() throws InvalidIdLengthException;
}
